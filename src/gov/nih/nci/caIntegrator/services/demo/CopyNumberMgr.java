package gov.nih.nci.caIntegrator.services.demo;



/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 30, 2005
 * Time: 5:48:41 PM
 */

public interface CopyNumberMgr extends javax.ejb.EJBObject {
    public String getCNData(String geneSymbol, QuantitationType type) throws java.rmi.RemoteException ;
}
