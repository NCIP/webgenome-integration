package gov.nih.nci.caIntegrator.services.bioAssay.ejb;


import javax.ejb.EJBLocalObject;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;
import org.rti.webgenome.client.ExperimentDTO;

/**
 * @author Ram Bhattaru
 */


public interface RBTBioAssayMgrLocal extends EJBLocalObject {

    /**
     * This method retrieves Experiment (group of BioAssays) for experiment ID passed in and
     * based on BioAssayDataConstraints specified
     * @param experimentID ID of the BioAssay Experiment to be retrieved
     * @param bioAssayDataConstraints This is the bioAssayConstraints object that contains search criteria for BioAssay
     * @param clientID Used for retrieving earlier saved application state
     * @return Returns the retrieved Experiment data as ExperimentDTO
     * @throws Exception
    */


    public ExperimentDTO getExperiment(String experimentID, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws Exception;
     /**
     * This method retrieves Experiments (groups of BioAssays) for experiment IDs passed in and
     * based on BioAssayDataConstraints specified
     * @param experimentIDs
     * @param bioAssayDataConstraints This is the bioAssayConstraints object that contains search criteria for BioAssay
     * @param clientID Used for retrieving earlier saved application state
     * @return Returns the retrieved Experiment data as an array of ExperimentDTOs
     * @throws Exception
     */
    public ExperimentDTO[] getExperiments(String[] experimentIDs, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws Exception;
}
