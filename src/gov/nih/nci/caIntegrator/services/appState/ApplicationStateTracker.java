package gov.nih.nci.caIntegrator.services.appState;

import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 * @author Ram Bhattaru
 */


public interface ApplicationStateTracker extends Remote{
  /**
   * This method publishes the report state (passed as TrackableAppState) and returns
   * the auto-generated ID under which it was saved.  The client can use this ID to retieve
   * this report state (as TrackableAppState) at later point of time before the the
   * predefined time expires
   * @param reportStateDTO Report state (application state) to be published
   * @return Returns the ID of applcation state that was published
 */
 public Integer publishReportState(TrackableAppState reportStateDTO) throws RemoteException,  Exception;

 /**
  *  This method returns the report state (as TrackableAppState) for client ID passed in
  * @param clientID ID of the client to be retrieved
  * @return Returns earlier published applcation state for the clientID passed in
  * @throws Exception  Will throw Exception if no state exists
 */
 public TrackableAppState getReportState(Integer clientID ) throws RemoteException, Exception;
}
