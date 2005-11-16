package gov.nih.nci.caIntegrator.services.appState.ejb;

import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTrackerHome;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 20, 2005
 * Time: 2:15:03 AM
 */

public interface RBTApplicationStateTrackerHome extends ApplicationStateTrackerHome, EJBHome {
    public static String JNDI_NAME="ReportStateTrackerService";
}
