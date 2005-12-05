package gov.nih.nci.caIntegrator.services.bioAssay.ejb;

import org.rti.webgenome.client.*;
import org.apache.log4j.PropertyConfigurator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import gov.nih.nci.caIntegrator.services.bioAssay.dto.ExperimentDTOImpl;
import gov.nih.nci.caIntegrator.services.bioAssay.BioAssayService;
import gov.nih.nci.caIntegrator.services.bioAssay.GeneExprDataService;
import gov.nih.nci.caIntegrator.services.bioAssay.*;
import gov.nih.nci.caIntegrator.services.bioAssay.LOHDataService;
import gov.nih.nci.caIntegrator.services.util.ServiceLocator;
import gov.nih.nci.caIntegrator.services.appState.ejb.RBTApplicationStateTrackerHome;
import gov.nih.nci.caIntegrator.services.appState.ejb.RBTApplicationStateTracker;
import gov.nih.nci.caIntegrator.services.appState.dto.RBTReportStateDTO;
import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTrackerHome;
import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTracker;
import gov.nih.nci.rembrandt.util.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 29, 2005
 * Time: 9:37:04 AM
 */
public class BioAssayServiceBean extends AbstractServiceBean implements BioAssayMgr {
    private BioAssayService service;


    private static org.apache.log4j.Logger logger_ =
        org.apache.log4j.Logger.getLogger(BioAssayServiceBean.class);

    public void ejbCreate() throws CreateException {
           //TODO: FIx this
        ApplicationContext.init();
        logger_.debug("ejbCreate()");
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

    public ExperimentDTO getExperiment(String experimentID, BioAssayDataConstraints assayDataConstraints, String clientID) throws Exception {

        logger_.debug("\n\n\n\n*******************BEGIN  NEW EXPERIMENT (" + experimentID +
                ") REQUEST ***********************");
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
        BioAssayDTO[] bioAssays = service.getBioAssays(bioAssayIDS, assayDataConstraints, reporterNames);

        String assayMessage = "Total BioAssays Returned {0} for client ID: {1}:";
        String assayLogMessage = MessageFormat.format(assayMessage, new Object[] {bioAssays.length, clientID});
        logger_.debug(assayLogMessage);

        ExperimentDTOImpl exptDTO = new ExperimentDTOImpl();
        exptDTO.setExperimentID(experimentID);
        exptDTO.setBioAssays(bioAssays);

        logger_.debug("\n\n****************** END EXPERIMENT (" + experimentID +
                ") REQUEST ***********************\n");


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

    public ExperimentDTO[] getExperiments(String[] experimentIDs, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws Exception {
        ExperimentDTO[] resultantDTOs = new ExperimentDTO[experimentIDs.length];
        for (int i = 0; i < experimentIDs.length; i++) {
            String experimentID = experimentIDs[i];
            resultantDTOs[i] = getExperiment(experimentID, bioAssayDataConstraints, clientID );
        }
        return resultantDTOs;
    }
}