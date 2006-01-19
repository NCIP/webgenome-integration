package gov.nih.nci.caIntegrator.services.bioAssay.ejb;

import gov.nih.nci.caIntegrator.services.bioAssay.ejb.RBTBioAssayMgrLocal;

import javax.ejb.EJBLocalHome;

/**
 * @author Ram Bhattaru
 */


public interface RBTBioAssayMgrLocalHome extends EJBLocalHome {
    RBTBioAssayMgrLocal create() throws  javax.ejb.CreateException;
}
