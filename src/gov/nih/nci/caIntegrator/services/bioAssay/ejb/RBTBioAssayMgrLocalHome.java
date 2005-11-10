package gov.nih.nci.caIntegrator.services.bioAssay.ejb;

import gov.nih.nci.caIntegrator.services.bioAssay.ejb.RBTBioAssayMgrLocal;

import javax.ejb.EJBLocalHome;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 31, 2005
 * Time: 3:22:42 AM
 */
public interface RBTBioAssayMgrLocalHome extends EJBLocalHome {
    RBTBioAssayMgrLocal create() throws  javax.ejb.CreateException;
}
