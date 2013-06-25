/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.bioAssay.ejb;


import javax.ejb.SessionContext;

/**
 * @author Ram Bhattaru
 */



/**
 * This class serves as base class for all EJBs in the app there by giving a
 * better control for common functionality
 */

abstract public class AbstractServiceBean implements javax.ejb.SessionBean {

    protected org.apache.log4j.Logger logger_ =
            org.apache.log4j.Logger.getLogger(AbstractServiceBean.class);

    protected SessionContext sessionCtx_;


    public void ejbActivate() throws javax.ejb.EJBException, java.rmi.RemoteException {
            logger_.debug("ejbActivate()");
    }

    public void ejbPassivate() throws javax.ejb.EJBException, java.rmi.RemoteException {
            logger_.debug("ejbPassivate()");
    }

    /**
    * @param sessionCtx session context to be set
    */
    public void setSessionContext(SessionContext sessionCtx) throws javax.ejb.EJBException, java.rmi.RemoteException {
            logger_.debug("setSessionContext()");
            this.sessionCtx_ = sessionCtx;
    }
}


