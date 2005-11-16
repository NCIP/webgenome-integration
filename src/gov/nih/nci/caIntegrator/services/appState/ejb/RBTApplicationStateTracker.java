package gov.nih.nci.caIntegrator.services.appState.ejb;

import gov.nih.nci.caIntegrator.services.appState.TrackableAppState;
import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTracker;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 21, 2005
 * Time: 6:32:44 AM
 */
public interface RBTApplicationStateTracker extends  ApplicationStateTracker, EJBObject {


}
