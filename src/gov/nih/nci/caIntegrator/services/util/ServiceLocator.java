package gov.nih.nci.caIntegrator.services.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBHome;
import javax.rmi.PortableRemoteObject;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 21, 2005
 * Time: 8:37:55 AM
 */
public class ServiceLocator {
   private static InitialContext initialContext;
   private Map cache;
   private String deployment ;

   private static ServiceLocator ONLY_INSTANCE;

   static  {
           ONLY_INSTANCE = new ServiceLocator();
   }
   static public ServiceLocator getInstance() {
       return ONLY_INSTANCE;
   }

   public  Object locateHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException {
         if (environment != null)
             initialContext = new javax.naming.InitialContext(environment);
         Object objRef ;
         if (cache.containsKey(jndiName)) {
                objRef  = cache.get(jndiName);
         } else {
                Object remObjRef = initialContext.lookup(jndiName);
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

   public Object relocateHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException {
       // remove old reference so that it can be re-intialized
       cache.remove(jndiName);
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

   public Object getLocalHome(String jndiHomeName) throws Exception{
        Object localHome = null;
        try {
            if (cache.containsKey(jndiHomeName)) {
                localHome = cache.get(jndiHomeName);
            } else {
                localHome = initialContext.lookup(jndiHomeName);
                cache.put(jndiHomeName, localHome);
            }
        } catch(NamingException ne) {
            ne.printStackTrace();
            throw new Exception(ne);
        }
        return localHome;
    }

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
