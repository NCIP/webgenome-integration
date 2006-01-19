package gov.nih.nci.caIntegrator.services.demo;

import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * @author Ram Bhattaru
 */

/**
 * This interface is only for demo purpose and not being used by the actual WGI integration module.
*/
public interface CopyNumberMgrHome extends EJBHome {
    CopyNumberMgr create()
            throws RemoteException, javax.ejb.CreateException;
}
