package gov.nih.nci.caIntegrator.services.appState.ejb;

import gov.nih.nci.caIntegrator.services.bioAssay.ejb.AbstractServiceBean;
import gov.nih.nci.caIntegrator.services.appState.ApplicationStateService;
import gov.nih.nci.caIntegrator.services.appState.TrackableAppState;
import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTracker;

import javax.ejb.CreateException;

/**
 * @author Ram Bhattaru
 */

/**
 * This class implements ApplicationStateTracker as EJB Stateless Session bean
 */

public class ApplicationStateTrackerBean extends AbstractServiceBean implements ApplicationStateTracker {

    private static org.apache.log4j.Logger logger_ =
        org.apache.log4j.Logger.getLogger(ApplicationStateTrackerBean .class);

    public void ejbCreate() throws CreateException {
        logger_.debug("ejbCreate()");
    }

    public void ejbRemove() throws javax.ejb.EJBException{
        logger_.debug("ejbRemove()");
    }

    /**
     *  This method publishes the application state (passed as TrackableAppState) and returns
     *  the auto-generated ID under which it was saved.  The client can use this ID to retieve this application
     *  state (as TrackableAppState) at later point of time before the the predefined time expires
     *
     * @param stateDTO application state to be published
     * @return Returns the generated ID of the saved application state
    */
    public Integer publishReportState(TrackableAppState stateDTO) throws Exception{
        Integer stateID = ApplicationStateService.publishState(stateDTO);
        logger_.debug("Application State published with state ID: " + stateID);

        return stateID;
    }

    /***
     *  This method retrieves earlier saved application state (as TrackableAppState)
     *  for the ID passed
     * @param appStateID ID of the application state to be retrieved
     * @return Returns the corresponding applcation state as TrackableAppState (with that ID)
     * @throws Exception will throw Exception if no state exists
    */
    public TrackableAppState getReportState(Integer appStateID) throws Exception {
        return ApplicationStateService.getState(appStateID);
    }
}
