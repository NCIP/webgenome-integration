/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.demo;

import gov.nih.nci.caIntegrator.services.bioAssay.ejb.AbstractServiceBean;
import gov.nih.nci.caintegrator.dto.critieria.AssayPlatformCriteria;
import gov.nih.nci.caintegrator.dto.critieria.Constants;
import gov.nih.nci.caintegrator.dto.critieria.GeneIDCriteria;
import gov.nih.nci.caintegrator.dto.de.AssayPlatformDE;
import gov.nih.nci.caintegrator.dto.de.GeneIdentifierDE;
import gov.nih.nci.caintegrator.dto.query.QueryType;
import gov.nih.nci.rembrandt.dto.query.ComparativeGenomicQuery;
import gov.nih.nci.rembrandt.queryservice.QueryManager;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.QueryProcessor;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.cgh.CopyNumber;
import gov.nih.nci.rembrandt.queryservice.resultset.ResultSet;
import gov.nih.nci.rembrandt.util.WGIContext;

import javax.ejb.CreateException;

/**
 * @author Ram Bhattaru
 */




/**
* 
* 
*/

/**
 * This class is only for demo purpose and not being used by the actual WGI integration module.
 * This class serves as a simple demo for retrieving Copy Number data from caIntegrator
 * data repository using Rembrandt Query service.
*/
public class CopyNumberMgrBean extends AbstractServiceBean {
    public CopyNumberMgrBean() {
    }

    private static org.apache.log4j.Logger logger_ =
        org.apache.log4j.Logger.getLogger(CopyNumberMgrBean.class);

    public void ejbCreate() throws CreateException {
           //TODO: FIx this
        WGIContext.init();
        logger_.debug("ejbCreate()");
    }

    public void ejbRemove() throws javax.ejb.EJBException{
        logger_.debug("ejbRemove()");
    }

    /**
     * This mehod retrives Copy Number Fact objects using Rembrandt's Query
     * object model.  It constructs the query, exceutes and formats the results
     * @param geneSymbol serves as Search criteria for copy number fact objects
     * @param type This will be one of the Quantitation types (such as Copy Number)
     * @return Returns copy number object data concatenated as a String
     */
    public String getCNData(String geneSymbol, QuantitationType type) {
        ComparativeGenomicQuery q = (ComparativeGenomicQuery) QueryManager.createQuery(QueryType.CGH_QUERY_TYPE);
        q.setQueryName("Test CGH Query");
        GeneIDCriteria  geneIDCrit = new GeneIDCriteria();
        GeneIdentifierDE.GeneSymbol gs = new GeneIdentifierDE.GeneSymbol(geneSymbol);
        geneIDCrit.setGeneIdentifier(gs);
        q.setGeneIDCrit(geneIDCrit);

            AssayPlatformCriteria crit = new AssayPlatformCriteria();
            crit.setAssayPlatformDE(new AssayPlatformDE(Constants.AFFY_100K_SNP_ARRAY));
            q.setAssayPlatformCrit(crit);
            StringBuffer out = new StringBuffer();
            try {
                ResultSet[] cghObjects = QueryProcessor.execute(q);
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
            }
        return out.toString();
    }
}
