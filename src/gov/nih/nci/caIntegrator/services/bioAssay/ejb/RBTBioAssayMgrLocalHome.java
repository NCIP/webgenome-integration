/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.bioAssay.ejb;

import javax.ejb.EJBLocalHome;

/**
 * @author Ram Bhattaru
 */



/**
* 
* 
*/

public interface RBTBioAssayMgrLocalHome extends EJBLocalHome {
    RBTBioAssayMgrLocal create() throws  javax.ejb.CreateException;
}
