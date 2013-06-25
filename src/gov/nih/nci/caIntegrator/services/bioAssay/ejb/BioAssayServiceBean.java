/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.bioAssay.ejb;

import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTracker;
import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTrackerHome;
import gov.nih.nci.caIntegrator.services.appState.dto.RBTReportStateDTO;
import gov.nih.nci.caIntegrator.services.appState.ejb.RBTApplicationStateTrackerHome;
import gov.nih.nci.caIntegrator.services.bioAssay.BioAssayService;
import gov.nih.nci.caIntegrator.services.bioAssay.CopyNumberDataService;
import gov.nih.nci.caIntegrator.services.bioAssay.GeneExprDataService;
import gov.nih.nci.caIntegrator.services.bioAssay.LOHDataService;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.ExperimentDTOImpl;
import gov.nih.nci.caIntegrator.services.util.ServiceLocator;
import gov.nih.nci.rembrandt.util.WGIContext;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;
import org.rti.webgenome.client.BioAssayMgr;
import org.rti.webgenome.client.ExperimentDTO;
import org.rti.webgenome.client.QuantitationTypes;

/**
 * @author Ram Bhattaru
 */




/**
 * This class is the EJB implementation of BioAssayMgr business interface.
 */

public class BioAssayServiceBean extends AbstractServiceBean implements BioAssayMgr {
    private BioAssayService service;


    private static org.apache.log4j.Logger logger_ =
        org.apache.log4j.Logger.getLogger(BioAssayServiceBean.class);

    public void ejbCreate() throws CreateException {
        logger_.debug("ejbCreate()");
        WGIContext.init();
        logger_.debug("DATA SOURCE DETAILS: ");
        logger_.debug(System.getProperty("gov.nih.nci.wgi.dbalias"));
        logger_.debug(System.getProperty("gov.nih.nci.wgi.jcd_alias"));
    }

    public void ejbRemove() throws javax.ejb.EJBException{
        logger_.debug("ejbRemove()");
    }

    private void createServiceType(String serviceType) throws Exception {
            if (serviceType.equals(QuantitationTypes.COPY_NUMBER))
                service = new CopyNumberDataService();
            else if (serviceType.equals(QuantitationTypes.LOH))
                service = new LOHDataService();
            else if (serviceType.equals(QuantitationTypes.FOLD_CHANGE))
                service = new GeneExprDataService();
            else throw new Exception("Measurement Not Supported: " + serviceType);
    }

    /**
     * This method retrieves Experiment (group of BioAssays) for experiment ID passed in and
     * based on BioAssayDataConstraints specified
     * @param experimentID ID of the BioAssay Experiment to be retrieved
     * @param assayDataConstraints This is the bioAssayConstraints object that contains search criteria for BioAssay
     * @param clientID Used for retrieving earlier saved application state
     * @return Returns the retrieved Experiment data as ExperimentDTO
     * @throws Exception
     */
    public ExperimentDTO getExperiment(String experimentID, BioAssayDataConstraints assayDataConstraints, String clientID) throws Exception {

        logger_.debug("\n\n\n\n*********** BEGIN  NEW EXPERIMENT (" + experimentID +
                ") REQUEST FOR: " + clientID + "********");

        String message = "Received Request for experiment ID:{0} from client ID: {1}";
        String logMessage = MessageFormat.format(message, new Object[] {experimentID, clientID});
        logger_.debug(logMessage);

        String serviceType = assayDataConstraints.getQuantitationType();
        createServiceType(serviceType);
        assert (service != null);

        // retrieve the report state that got saved earlier for this clientID
        ApplicationStateTracker stateService = getAppStateTrackerService();
        RBTReportStateDTO state = null;
        try {
            Integer stateID = new Integer(clientID);
            state = (RBTReportStateDTO) stateService.getReportState(stateID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            /* this means the clientID passed was illegal string.  It should purely be a number as that
               is what this service creates and returns when the state was saved earlier */
            throw new Exception ("No State exists for application state ID: " + clientID);
        }

        assert(state != null);

        HashMap groups = state.getGroups();
        String[] bioAssayIDS = (String[]) groups.get(experimentID);
        List reporterNames = state.getSelectedReporerNames();
        BioAssayDTO[] bioAssays = service.getBioAssays(bioAssayIDS, assayDataConstraints, reporterNames, clientID);

        String assayMessage = "TOTAL BIOASSAYS RETURNED: {0} FOR CLIENT ID: {1}:";
        String assayLogMessage = MessageFormat.format(assayMessage, new Object[] {bioAssays.length, clientID});
        logger_.debug(assayLogMessage);

        ExperimentDTOImpl exptDTO = new ExperimentDTOImpl();
        exptDTO.setExperimentID(experimentID);
        exptDTO.setBioAssays(bioAssays);

        logger_.debug("\n\n************ END EXPERIMENT (" + experimentID +
                ") REQUEST FOR: " + clientID + "*******\n");


        return exptDTO;
    }

    private ApplicationStateTracker getAppStateTrackerService() throws Exception {
        try {
             ServiceLocator locator = ServiceLocator.getInstance();
             Object h = locator.locateHome(null, RBTApplicationStateTrackerHome.JNDI_NAME,
                                             ApplicationStateTrackerHome.class);
             ApplicationStateTrackerHome home = (ApplicationStateTrackerHome)h;
             ApplicationStateTracker  service = home.create();
             return service;
        } catch (NamingException ne) {
             ne.printStackTrace();
             throw new Exception(ne);
         } catch (CreateException ce) {
             ce.printStackTrace();
             throw  new Exception(ce);
         }
    }

    /**
     * This method retrieves Experiments (groups of BioAssays) for experiment IDs passed in and
     * based on BioAssayDataConstraints specified
     *
     * @param experimentIDs IDs of the BioAssay Experiments to be retrieved
     * @param bioAssayDataConstraints This is the bioAssayConstraints object that contains search criteria for BioAssay
     * @param clientID Used for retrieving earlier saved application state
     * @return Returns the retrieved Experiment data as an array of ExperimentDTOs
     * @throws Exception
     */
    public ExperimentDTO[] getExperiments(String[] experimentIDs, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws Exception {
        ExperimentDTO[] resultantDTOs = new ExperimentDTO[experimentIDs.length];
        for (int i = 0; i < experimentIDs.length; i++) {
            String experimentID = experimentIDs[i];
            resultantDTOs[i] = getExperiment(experimentID, bioAssayDataConstraints, clientID );
        }
        return resultantDTOs;
    }
}
