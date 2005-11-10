package gov.nih.nci.caIntegrator.services.appState.ejb;

import gov.nih.nci.caIntegrator.services.appState.dto.RBTReportStateDTO;
import gov.nih.nci.caIntegrator.services.appState.TrackableAppState;

import javax.ejb.EJBLocalObject;



/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 20, 2005
 * Time: 2:15:03 AM
 */

public interface RBTApplicationStateTrackerLocal extends EJBLocalObject {
    /* this method publishes the report state (passed as TrackableAppState) and returns
       the auto-generated ID under which it was saved.  The client can use this ID to retieve
       this report state (as TrackableAppState) at later point of time before the the
       predefined time expires
    */
    public Integer publishReportState(TrackableAppState reportStateDTO) throws  Exception;

    /* this method returns the report state (as TrackableAppState) for appStateID */
    public TrackableAppState getReportState(Integer clientID ) throws Exception;
}
