package gov.nih.nci.caIntegrator.services.demo;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Mar 30, 2005
 * Time: 5:48:41 PM
 */

public interface CopyNumberMgrHome extends EJBHome {
    CopyNumberMgr create()
            throws RemoteException, javax.ejb.CreateException;
}
