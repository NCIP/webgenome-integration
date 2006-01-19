package gov.nih.nci.caIntegrator.services.appState;

import java.rmi.RemoteException;

/**
 * @author Ram Bhattaru
 */


public interface ApplicationStateTrackerHome  {
    ApplicationStateTracker create() throws RemoteException, javax.ejb.CreateException;
}
