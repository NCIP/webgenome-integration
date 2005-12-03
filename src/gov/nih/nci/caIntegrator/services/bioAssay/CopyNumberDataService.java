package gov.nih.nci.caIntegrator.services.bioAssay;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;
import org.rti.webgenome.client.QuantitationTypes;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.BioAssayDTOImpl;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.BioAssayDatumDTOImpl;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.ReporterDTOImpl;
import gov.nih.nci.rembrandt.dto.query.ComparativeGenomicQuery;
import gov.nih.nci.rembrandt.queryservice.resultset.ResultSet;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.QueryProcessor;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.cgh.CopyNumber;
import gov.nih.nci.rembrandt.queryservice.QueryManager;
import gov.nih.nci.caintegrator.dto.critieria.SampleCriteria;
import gov.nih.nci.caintegrator.dto.critieria.AssayPlatformCriteria;
import gov.nih.nci.caintegrator.dto.critieria.Constants;
import gov.nih.nci.caintegrator.dto.critieria.RegionCriteria;
import gov.nih.nci.caintegrator.dto.de.AssayPlatformDE;
import gov.nih.nci.caintegrator.dto.de.ChromosomeNumberDE;
import gov.nih.nci.caintegrator.dto.de.BasePairPositionDE;
import gov.nih.nci.caintegrator.dto.de.SampleIDDE;
import gov.nih.nci.caintegrator.dto.query.QueryType;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 29, 2005
 * Time: 10:24:03 AM
 */
public class CopyNumberDataService implements BioAssayService{
     private static org.apache.log4j.Logger logger_ =
        org.apache.log4j.Logger.getLogger(CopyNumberDataService .class);

    public BioAssayDTO getBioAssay(String bioAssayId, List selectedReporters) {
        return null;
    }

    public BioAssayDTO[] getBioAssays(String[] bioAssayIds, BioAssayDataConstraints constraints, List selectedReporters) throws Exception{

        // 1. parse the constraints object and build corresponding ComparativeGenomicQuery
        ComparativeGenomicQuery q = buildCopyNumberQuery(constraints, bioAssayIds);

        logger_.debug(q.toString());
        System.out.println(q.toString());
        // 2. execute the actual Query
        long t0 = System.currentTimeMillis();
        ResultSet[] cghObjects = QueryProcessor.execute(q);
        long t1 = System.currentTimeMillis();
        Double t = (t1-t0)/(1000 * 60.0);
        logger_.debug("Time taken to process the query: " + t);
        System.out.println("\"Time taken to process the query: \" + t");
        // 3. now format result objects
        /* 3.1. Store CopyNuber result objects per sampleID
                as a collection of CopyNumber objects using sampleID as key
        */
        HashMap<String, Collection<CopyNumber>> h = new HashMap<String, Collection<CopyNumber>>();
        groupCopyNumberObjsPerSample(cghObjects, h);

        //  3.2 format BioAssayDatumDTO objects from results
        BioAssayDTOImpl[] bioAssayDTOs = new BioAssayDTOImpl[h.size()];
        Iterator bioAssayIDsIter = h.keySet().iterator();
        int assayIndex = 0;
        while (bioAssayIDsIter.hasNext()) {
            // for each bioSampleID, create a BioAssayDTO object
            BioAssayDTOImpl bioAssayDTO = new BioAssayDTOImpl();
            String bioAssayID = (String) bioAssayIDsIter.next();
            bioAssayDTO.setID(bioAssayID);
            bioAssayDTO.setName(bioAssayID);
            Collection<CopyNumber> cnCol =  (Collection<CopyNumber>) h.get(bioAssayID);

            // create BioAssayDatumDTOImpl[] objects for this bioAssay object
            BioAssayDatumDTOImpl[] datumDTOs = new BioAssayDatumDTOImpl[cnCol.size()];

            // populate these BioAssayDatumDTOImpl[] objects from Copy Number objects
            int datumIndex=0;
            for (Iterator<CopyNumber> iterator = cnCol.iterator(); iterator.hasNext();) {
                CopyNumber copyNumber =  iterator.next();
                String snpProbeName = copyNumber.getSnpProbesetName();

                // create correponding ReporterDTO object
                ReporterDTOImpl reporterDTO = new ReporterDTOImpl();
                reporterDTO.setName(snpProbeName);
                reporterDTO.setSelected(
                        new Boolean(selectedReporters.contains(snpProbeName)));
                CopyNumber.SNPAnnotation annot = copyNumber.getAnnotations();
                if (annot != null ) {
                    Set geneSymbols = annot.getGeneSymbols();
                    if (geneSymbols != null) {
                        String[] genes = new String[geneSymbols.size()];
                        genes = (String[]) geneSymbols.toArray(genes);
                        reporterDTO.setAssociatedGenes(genes);
                    }
                    Set accessionsNumbers = annot.getAccessionNumbers();
                    if (accessionsNumbers != null) {
                        String[] accessions = new String[accessionsNumbers.size()];
                        accessions = (String[]) accessionsNumbers.toArray(accessions);
                        reporterDTO.setAnnotations(accessions);
                    }
                }
                reporterDTO.setChromosome(copyNumber.getChromosome());
                reporterDTO.setChromosomeLocation(copyNumber.getPhysicalPosition());

                // Now format BioAssayDatumDTO object
                BioAssayDatumDTOImpl datumImpl = new BioAssayDatumDTOImpl ();
                datumImpl.setReporter(reporterDTO);
                datumImpl.setQuantitationType(QuantitationTypes.COPY_NUMBER);
                // TODO: confirm with Subha about copyNumber or inverse of copyNumber value
                datumImpl.setValue(copyNumber.getCopyNumber());
                datumDTOs[datumIndex++] = datumImpl;
            }
            bioAssayDTO.setBioAssayData(datumDTOs);
            bioAssayDTOs[assayIndex++] = bioAssayDTO;
        }
        return bioAssayDTOs;
    }

    private ComparativeGenomicQuery buildCopyNumberQuery(BioAssayDataConstraints constraints, String[] bioAssayIds) {
        String chromosome = constraints.getChromosome();
        Long startPos = constraints.getStartPosition();
        Long endPos = constraints.getEndPosition();

        ComparativeGenomicQuery q = (ComparativeGenomicQuery) QueryManager.createQuery(
                                            QueryType.CGH_QUERY_TYPE);

        RegionCriteria regionCriteria = buildRegionCriteria(chromosome, startPos, endPos);
        if(regionCriteria != null)
            q.setRegionCrit(regionCriteria);

        Collection<SampleIDDE> samples = new ArrayList<SampleIDDE>();
        for (int i = 0; i < bioAssayIds.length; i++) {
            SampleIDDE sampleDE = new SampleIDDE(bioAssayIds[i]);
            samples.add(sampleDE);
        }

        SampleCriteria sampleCrit = new SampleCriteria();
        sampleCrit.setSampleIDs(samples);
        q.setSampleIDCrit(sampleCrit);

        //TODO: Confirm this platform stuff with Subha/David
        AssayPlatformCriteria platformCrit = new AssayPlatformCriteria();
        platformCrit.setAssayPlatformDE(new AssayPlatformDE(Constants.AFFY_100K_SNP_ARRAY));
        q.setAssayPlatformCrit(platformCrit);
        return q;
    }

    private RegionCriteria buildRegionCriteria(String chromosome, Long startPos, Long endPos) {
        RegionCriteria regionCriteria = null;
        if (chromosome != null) {
            ChromosomeNumberDE chromosomeDE = new ChromosomeNumberDE(chromosome);
            regionCriteria = new RegionCriteria();
            regionCriteria.setChromNumber(chromosomeDE);

            //TODO: confirm with Subha/David about using start and end positions defaults
            /* if start and end postions not specified in the criteria, use the start position of
               p th arm and end position of q th arm */
/*
            if ((startPos == null)  && (endPos == null)) {
                startPos = new Long("0");
                endPos = new Long("158545518");
            }
*/

            BasePairPositionDE.StartPosition start =
                    new BasePairPositionDE.StartPosition(startPos);
            BasePairPositionDE.EndPosition end =
                    new BasePairPositionDE.EndPosition(endPos);
            regionCriteria.setStart(start);
            regionCriteria.setEnd(end);
        }
        return regionCriteria;
    }

    private void groupCopyNumberObjsPerSample(ResultSet[] cghObjects, HashMap<String, Collection<CopyNumber>> h) {
        try {
        for (int i = 0; i < cghObjects.length; i++) {
            CopyNumber cghObject = (CopyNumber) cghObjects[i];
            String bioAssayID = cghObject.getSampleId();

            // retrieve corresponding copyNumber object collection for this sampleID
            Collection<CopyNumber> copyNumberCol  = h.get(bioAssayID);
            if (copyNumberCol == null) {
                copyNumberCol  = new ArrayList<CopyNumber>();
                h.put(bioAssayID, copyNumberCol);
            }
            // add this copyNumber object to the correspinding copyNumber object collection
            copyNumberCol.add(cghObject);
        }
        }catch(Throwable t) {
            t.printStackTrace();
        }
    }


}
