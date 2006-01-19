package gov.nih.nci.caIntegrator.services.appState.ejb;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

/**
 * @author Ram Bhattaru
 */

public interface RBTApplicationStateTrackerLocalHome extends EJBLocalHome {
    public static String JNDI_NAME="ReportStateTrackerService";
    RBTApplicationStateTrackerLocal create() throws CreateException;
}
