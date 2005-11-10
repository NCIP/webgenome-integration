package gov.nih.nci.caIntegrator.services.demo;

import gov.nih.nci.caIntegrator.services.bioAssay.ejb.AbstractServiceBean;
import gov.nih.nci.rembrandt.dto.query.ComparativeGenomicQuery;
import gov.nih.nci.rembrandt.queryservice.QueryManager;
import gov.nih.nci.rembrandt.queryservice.resultset.ResultSet;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.QueryProcessor;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.cgh.CopyNumber;
import gov.nih.nci.rembrandt.util.ApplicationContext;
import gov.nih.nci.caintegrator.dto.query.QueryType;
import gov.nih.nci.caintegrator.dto.critieria.GeneIDCriteria;
import gov.nih.nci.caintegrator.dto.critieria.AssayPlatformCriteria;
import gov.nih.nci.caintegrator.dto.critieria.Constants;
import gov.nih.nci.caintegrator.dto.de.GeneIdentifierDE;
import gov.nih.nci.caintegrator.dto.de.AssayPlatformDE;

import javax.ejb.CreateException;
import java.rmi.RemoteException;


/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 30, 2005
 * Time: 5:48:41 PM
*/

public class CopyNumberMgrBean extends AbstractServiceBean {
    public CopyNumberMgrBean() {
    }

    private static org.apache.log4j.Logger logger_ =
        org.apache.log4j.Logger.getLogger(CopyNumberMgrBean.class);

    public void ejbCreate() throws CreateException {
           //TODO: FIx this
        ApplicationContext.init();
        logger_.debug("ejbCreate()");
    }

    public void ejbRemove() throws javax.ejb.EJBException{
        logger_.debug("ejbRemove()");
    }

    public String getCNData(String geneSymbol, QuantitationType type) {
      if (type.equals(QuantitationType.COPY_NUMBER)) {     
          System.out.println("It works");
      }
      if  (type instanceof QuantitationType.QuantitationTypeImpl) {
          System.out.println("INSTANCE OF WORKS OUT");
      }
      ComparativeGenomicQuery q = (ComparativeGenomicQuery) QueryManager.createQuery(QueryType.CGH_QUERY_TYPE);
         q.setQueryName("Test CGH Query");
        GeneIDCriteria  geneIDCrit = new GeneIDCriteria();
        GeneIdentifierDE.GeneSymbol gs = new GeneIdentifierDE.GeneSymbol(geneSymbol);
        geneIDCrit.setGeneIdentifier(gs);
         q.setGeneIDCrit(geneIDCrit);
            //q.setGeneOntologyCrit(ontologyCrit);
            //q.setRegionCrit(regionCrit);
            //q.setPathwayCrit(pathwayCrit);

            AssayPlatformCriteria crit = new AssayPlatformCriteria();
            crit.setAssayPlatformDE(new AssayPlatformDE(Constants.AFFY_100K_SNP_ARRAY));
            q.setAssayPlatformCrit(crit);
            //q.setRegionCrit(regionCrit);
            //q.setSNPCrit(snpCrit);
            //q.setAllGenesCrit(allGenesCriteria);
            //q.setDiseaseOrGradeCrit(diseaseCrit);
            //q.setCopyNumberCrit(copyNumberCrit);
            //q.setSampleIDCrit(sampleCrit);
            StringBuffer out = new StringBuffer();
            try {
                //ResultSet[] cghObjects = QueryManager.executeQuery(q);
                ResultSet[] cghObjects = QueryProcessor.execute(q);
                //print(geneExprObjects);
                //testResultset(geneExprObjects);

                out.append("Size: " + cghObjects.length);
                for (int i = 0; i < cghObjects.length; i++) {
                    CopyNumber cghObject =
                            (CopyNumber) cghObjects[i];
                    out.append("SampleID: " + cghObject.getSampleId() + " || Copy Number: "
                    + cghObject.getCopyNumber() + " || SNPProbesetName: " + cghObject.getSnpProbesetName()
                    + " || Chromosome: " + cghObject.getCytoband() );
                    if (cghObject.getAnnotations() != null)
                    out.append( "Annotation GeneSymbols: " +
                            cghObject.getAnnotations().getGeneSymbols()+
                       "  LocusLinks: " + cghObject.getAnnotations().getLocusLinkIDs() +
                    "  Accessions Numbers: " + cghObject.getAnnotations().getAccessionNumbers());
                }
            } catch(Throwable t ) {
                t.printStackTrace();
                //throw new RemoteException(t.toString());
            }
        return out.toString();
    }
}
