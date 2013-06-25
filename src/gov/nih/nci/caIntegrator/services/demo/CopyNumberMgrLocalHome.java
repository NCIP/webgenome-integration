/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.demo;

import javax.ejb.EJBLocalHome;

/**
 * @author Ram Bhattaru
 */





/**
* 
* 
*/

/**
 * This interface is only for demo purpose and not being used by the actual WGI integration module.
*/
public interface CopyNumberMgrLocalHome extends EJBLocalHome {
    CopyNumberMgrLocal create() throws  javax.ejb.CreateException;
}
