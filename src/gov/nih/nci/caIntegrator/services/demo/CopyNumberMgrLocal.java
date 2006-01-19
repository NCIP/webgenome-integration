package gov.nih.nci.caIntegrator.services.demo;

import javax.ejb.EJBLocalObject;

/**
 * @author: Ram Bhattaru
 * @Date: Mar 30, 2005
 */

/**
 *
 * This interface is only for demo purpose and not being used by the actual WGI integration module.
 * This is a local interface for CopyNumberMgr.
 */
public interface CopyNumberMgrLocal extends EJBLocalObject{
    public String getCNData(String geneSymbol, QuantitationType type) ;
}
