package gov.nih.nci.caIntegrator.services.util;

import gov.nih.nci.caIntegrator.services.appState.ejb.RBTApplicationStateTrackerHome;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 21, 2005
 * Time: 9:30:48 AM
 */
public class ApplicationStateTrackerHomeLocator {
     /** Cached remote home (EJBHome). Uses lazy loading to obtain its value (loaded by getHome() methods). */
   private static RBTApplicationStateTrackerHome cachedRemoteHome = null;

   private static Object lookupHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException {
      // Obtain initial context
      javax.naming.InitialContext initialContext = new javax.naming.InitialContext(environment);
      try {
         Object objRef = initialContext.lookup(jndiName);
         // only narrow if necessary
         if (narrowTo.isInstance(java.rmi.Remote.class))
            return javax.rmi.PortableRemoteObject.narrow(objRef, narrowTo);
         else
            return objRef;
      } finally {
         initialContext.close();
      }
   }

   // Home interface lookup methods

   /**
    * Obtain remote home interface from default initial context
    * @return Home interface for RBTApplicationStateTracker. Lookup using JNDI_NAME
    */
   public static RBTApplicationStateTrackerHome getHome() throws javax.naming.NamingException
   {
      if (cachedRemoteHome == null) {
            cachedRemoteHome = (RBTApplicationStateTrackerHome) lookupHome(null, RBTApplicationStateTrackerHome.JNDI_NAME,
                                RBTApplicationStateTrackerHome.class);
      }
      return cachedRemoteHome;
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for RBTApplicationStateTracker. Lookup using JNDI_NAME
    */
   public static RBTApplicationStateTrackerHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (RBTApplicationStateTrackerHome) lookupHome(environment,  RBTApplicationStateTrackerHome.JNDI_NAME, RBTApplicationStateTrackerHome.class);
   }
}
