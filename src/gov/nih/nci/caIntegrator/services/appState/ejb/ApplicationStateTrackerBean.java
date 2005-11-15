package gov.nih.nci.caIntegrator.services.appState.ejb;

import gov.nih.nci.caIntegrator.services.bioAssay.ejb.AbstractServiceBean;
import gov.nih.nci.caIntegrator.services.appState.ApplicationStateService;
import gov.nih.nci.caIntegrator.services.appState.TrackableAppState;
import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTracker;

import javax.ejb.CreateException;


/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 20, 2005
 * Time: 2:15:03 AM
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

    /*  method publishes the application state (passed as TrackableAppState type) and
        returns the auto-generated ID under which it was saved.  The client can use
        this ID to retrieve this application state (as TrackableAppState) at later point
        of time before the predefined time expires
    */
    public Integer publishReportState(TrackableAppState stateDTO) throws Exception{
        Integer stateID = ApplicationStateService.publishState(stateDTO);
        logger_.debug("Application State published with state ID: " + stateID);

        return stateID;
    }

    // returns application state as TrackableAppState object for the stateDTO
    public TrackableAppState getReportState(Integer stateDTO) throws Exception {
        return ApplicationStateService.getState(stateDTO);
    }
}
