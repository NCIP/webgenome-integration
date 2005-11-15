package gov.nih.nci.caIntegrator.services.appState;



import gov.nih.nci.caIntegrator.services.appState.ejb.RBTApplicationStateTracker;

import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 20, 2005
 * Time: 9:52:34 AM
 */
public interface ApplicationStateTrackerHome extends EJBHome {
    RBTApplicationStateTracker create() throws RemoteException, javax.ejb.CreateException;
}
