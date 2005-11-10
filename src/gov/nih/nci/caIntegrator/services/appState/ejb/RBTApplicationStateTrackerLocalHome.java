package gov.nih.nci.caIntegrator.services.appState.ejb;

import gov.nih.nci.caIntegrator.services.appState.ejb.RBTApplicationStateTrackerLocal;
import gov.nih.nci.caIntegrator.services.appState.ejb.RBTApplicationStateTrackerLocal;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 20, 2005
 * Time: 2:15:03 AM
 */

public interface RBTApplicationStateTrackerLocalHome extends EJBLocalHome {
    public static String JNDI_NAME="ReportStateTrackerService";
    RBTApplicationStateTrackerLocal create() throws CreateException;
}
