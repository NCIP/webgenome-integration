package gov.nih.nci.caIntegrator.services.bioAssay.ejb;


import javax.ejb.EJBLocalObject;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;
import org.rti.webgenome.client.ExperimentDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 31, 2005
 * Time: 3:19:38 AM
 */
public interface RBTBioAssayMgrLocal extends EJBLocalObject {
   public ExperimentDTO getExperiment(String experimentID, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws Exception;
   public ExperimentDTO[] getExperiments(String[] experimentIDs, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws Exception;
}
