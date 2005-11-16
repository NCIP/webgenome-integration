package gov.nih.nci.caIntegrator.services.appState;

import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 20, 2005
 * Time: 9:52:34 AM
 */
public interface ApplicationStateTrackerHome  {
    ApplicationStateTracker create() throws RemoteException, javax.ejb.CreateException;
}
