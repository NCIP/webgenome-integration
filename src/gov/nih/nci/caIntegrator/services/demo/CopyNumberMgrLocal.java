package gov.nih.nci.caIntegrator.services.demo;

import javax.ejb.EJBLocalObject;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Mar 30, 2005
 * Time: 5:48:41 PM
 */

public interface CopyNumberMgrLocal extends EJBLocalObject{
    public String getCNData(String geneSymbol, QuantitationType type) ;
}
