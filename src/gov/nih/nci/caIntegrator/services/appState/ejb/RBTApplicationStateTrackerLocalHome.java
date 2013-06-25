/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.appState.ejb;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

/**
 * @author Ram Bhattaru
 */



/**
* 
* 
*/

public interface RBTApplicationStateTrackerLocalHome extends EJBLocalHome {
    public static String JNDI_NAME="ReportStateTrackerService";
    RBTApplicationStateTrackerLocal create() throws CreateException;
}
