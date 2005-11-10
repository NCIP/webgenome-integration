package gov.nih.nci.caIntegrator.services.demo;

import gov.nih.nci.caIntegrator.services.demo.CopyNumberMgrLocal;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Mar 30, 2005
 * Time: 5:48:41 PM
 */

public interface CopyNumberMgrLocalHome extends EJBLocalHome {
    CopyNumberMgrLocal create() throws  javax.ejb.CreateException;
}
