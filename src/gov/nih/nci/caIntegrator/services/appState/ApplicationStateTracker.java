package gov.nih.nci.caIntegrator.services.appState;


import org.rti.webgenome.client.ExperimentDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;

import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 25, 2005
 * Time: 5:55:03 AM
 */
public interface ApplicationStateTracker {
       /* this method publishes the report state (passed as TrackableAppState) and returns
       the auto-generated ID under which it was saved.  The client can use this ID to retieve
       this report state (as TrackableAppState) at later point of time before the the
       predefined time expires
    */
    public Integer publishReportState(TrackableAppState reportStateDTO) throws RemoteException,  Exception;

    /* this method returns the report state (as TrackableAppState) for appStateID */
    public TrackableAppState getReportState(Integer clientID ) throws RemoteException, Exception;
}
