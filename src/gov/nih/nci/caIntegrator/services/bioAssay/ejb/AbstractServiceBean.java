package gov.nih.nci.caIntegrator.services.bioAssay.ejb;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.LogManager;

import javax.ejb.SessionContext;
import javax.ejb.SessionBean;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 28, 2005
 * Time: 3:35:00 PM
 */
abstract public class AbstractServiceBean implements javax.ejb.SessionBean {

    /*static {

            //PropertyConfigurator.configure("D:/Projects/caintegrator/WebGenomeEjb/log4j.xml");
            PropertyConfigurator.configure("D:/Projects/caintegrator/WebGenomeEjb/service-log4j.properties");
    }*/
    protected org.apache.log4j.Logger logger_ =
            org.apache.log4j.Logger.getLogger(AbstractServiceBean.class);

        protected SessionContext sessionCtx_;


        public void ejbActivate()
        throws javax.ejb.EJBException, java.rmi.RemoteException {
            logger_.debug("ejbActivate()");
        }

        public void ejbPassivate()
        throws javax.ejb.EJBException, java.rmi.RemoteException {
            logger_.debug("ejbPassivate()");
        }


        public void setSessionContext(SessionContext sessionCtx)
        throws javax.ejb.EJBException, java.rmi.RemoteException {
            logger_.debug("setSessionContext()");
            this.sessionCtx_ = sessionCtx;
        }
}


