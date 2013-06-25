/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.appState;

import java.rmi.RemoteException;

/**
 * @author Ram Bhattaru
 */




/**
* 
* 
*/

public interface ApplicationStateTrackerHome  {
    ApplicationStateTracker create() throws RemoteException, javax.ejb.CreateException;
}
