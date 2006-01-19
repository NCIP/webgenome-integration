package gov.nih.nci.caIntegrator.services.appState.ejb;

import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTrackerHome;

import javax.ejb.EJBHome;

/**
 * @author Ram Bhattaru
 */


public interface RBTApplicationStateTrackerHome extends ApplicationStateTrackerHome, EJBHome {
    public static String JNDI_NAME="ReportStateTrackerService";
}
