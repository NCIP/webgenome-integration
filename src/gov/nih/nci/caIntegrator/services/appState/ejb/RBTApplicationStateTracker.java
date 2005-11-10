package gov.nih.nci.caIntegrator.services.appState.ejb;

import gov.nih.nci.caIntegrator.services.appState.TrackableAppState;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 21, 2005
 * Time: 6:32:44 AM
 */
public interface RBTApplicationStateTracker extends  EJBObject {
     /* this method publishes the report state (passed as TrackableAppState) and returns
       the auto-generated ID under which it was saved.  The client can use this ID to retieve
       this report state (as TrackableAppState) at later point of time before the the
       predefined time expires
    */
    public Integer publishReportState(TrackableAppState reportStateDTO) throws RemoteException,  Exception;

    /* this method returns the report state (as TrackableAppState) for appStateID */
    public TrackableAppState getReportState(Integer clientID ) throws RemoteException, Exception;
}
