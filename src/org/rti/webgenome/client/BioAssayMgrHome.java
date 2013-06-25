/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package org.rti.webgenome.client;

import java.rmi.RemoteException;

/**
 * @author Ram Bhattaru
 */




/**
* 
* 
*/

/**
 * This defines Home interface for BioAssayMgr
 */
public interface BioAssayMgrHome {
    BioAssayMgr create() throws RemoteException, javax.ejb.CreateException;
}
