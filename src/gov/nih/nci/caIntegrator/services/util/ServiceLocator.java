package gov.nih.nci.caIntegrator.services.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.CommunicationException;
import javax.ejb.EJBHome;
import javax.rmi.PortableRemoteObject;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * @author Ram Bhattaru
 */

/**
 * This class will provide methods for locating services (such as locating EJB home interfaces)
 * 
 */

public class ServiceLocator {
   private  InitialContext initialContext;
   private Map cache;
   private String deployment ;

   private static ServiceLocator ONLY_INSTANCE;

   /***
     *  This method returns the only instance (singleton) of this class
     *  @return Returns only instance of this class
    */
   static public ServiceLocator getInstance() {
       if (ONLY_INSTANCE == null)
           ONLY_INSTANCE = new ServiceLocator();
       return ONLY_INSTANCE;
   }

    /**
     * This method is called before recreating the only instance of this class.
     * i.e. to reset the only instance of this class.
     * @param sl new instance of this class
     */
    static public void  setInstance(ServiceLocator sl) {
          ONLY_INSTANCE = null;
    }

    /**
     *  Obtain remote home interface from parameterised initial context
     * @param environment Parameters to use for creating initial context
     * @param jndiName  JNDI name of the home interface
     * @param narrowTo class of remote home interface
     * @return Returns the requested Remote Home
     * @throws javax.naming.NamingException
     */
    public  Object locateHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException {
         if (environment != null)
             initialContext = new javax.naming.InitialContext(environment);
         Object objRef ;
         if (cache.containsKey(jndiName)) {
                objRef  = cache.get(jndiName);
         } else {
                Object remObjRef = null;
                try {
                    remObjRef = initialContext.lookup(jndiName);
                } catch(CommunicationException ce) {
                    // server might have restarted.  So retry to locate
                    remObjRef = initialContext.lookup(jndiName);
                }
                // narrow only if necessary
                if (narrowTo.isInstance(java.rmi.Remote.class)) {
                    objRef = javax.rmi.PortableRemoteObject.narrow(remObjRef , narrowTo);
                } else {
                    objRef = remObjRef;
                }
                cache.put(jndiName, remObjRef);
         }
         return objRef;
   }

    /**
     *  This method is used to relocate the home interface.  This is used when the remote
     *  cached home interface reference becomes stale because of the container restart-up.
     *  At this time, the cache is cleared and the Singleton instance is re-initialized
     * @param environment Parameters to use for creating initial context
     * @param jndiName JNDI name of the home interface
     * @param narrowTo class of remote home interface
     * @return Returns the requested Remote Home
     * @throws javax.naming.NamingException
     * @throws Exception
     */

   public Object relocateHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException, Exception {
       // remove old reference so that it can be re-intialized
       cache.remove(jndiName);
       initializeContext();
       return locateHome(environment, jndiName, narrowTo);
   }

  private ServiceLocator() {
       try {
           initializeContext();
           cache = Collections.synchronizedMap(new HashMap());
       } catch(NamingException ne) {
           ne.printStackTrace();
       } catch(Exception e) {
           e.printStackTrace();
       }
   }

    /**
     * Obtain Local home interface from initial context
     * @param jndiHomeName JNDI name of the home interface
     * @return Returns home interface
     * @throws Exception
     */
    public Object getLocalHome(String jndiHomeName) throws Exception{
        Object localHome = null;
        try {
            if (cache.containsKey(jndiHomeName)) {
                localHome = cache.get(jndiHomeName);
            } else {
                localHome = initialContext.lookup(jndiHomeName);
                cache.put(jndiHomeName, localHome);
            }
        }  catch(CommunicationException ce) {
            localHome = initialContext.lookup(jndiHomeName);
            cache.put(jndiHomeName, localHome);
        } catch(NamingException ne) {
            ne.printStackTrace();
            throw new Exception(ne);
        }
        return localHome;
    }

    /**
     *  Obtain Remote home interface from initial context
     * @param jndiHomeName JNDI name of the home interface
     * @param homeClassName class of Remote Home interface
     * @return Returns remote home interface
     * @throws Exception
     */
    public EJBHome getRemoteHome(String jndiHomeName, Class homeClassName) throws Exception{
        EJBHome remoteHome = null;
        try {
            if (cache.containsKey(jndiHomeName)) {
                remoteHome  = (EJBHome) cache.get(jndiHomeName);
            } else {
                Object objRef = initialContext.lookup(jndiHomeName);
                Object obj = PortableRemoteObject.narrow(objRef, homeClassName);
                remoteHome = (EJBHome) obj;
                cache.put(jndiHomeName, remoteHome);
            }
        } catch(NamingException ne) {
            ne.printStackTrace();
            throw new Exception(ne);
        }
        return remoteHome;
    }
    private void initializeContext () throws Exception{

        try {
            Properties p = System.getProperties();
            InputStream is = ServiceLocator.class.getResourceAsStream("/jndi.properties");
            p.load(is);
            deployment = p.getProperty("jndi.deployment");
            initialContext = new InitialContext(p);
        } catch(IOException ioe) {
            throw new Exception(ioe);
        }
    }
    public String getDeployment() {
          return deployment;
    }
}
