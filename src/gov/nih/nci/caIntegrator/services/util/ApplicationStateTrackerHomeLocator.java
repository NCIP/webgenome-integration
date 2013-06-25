/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.util;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import gov.nih.nci.caIntegrator.services.appState.ejb.RBTApplicationStateTrackerHome;

/**
 * @author Ram Bhattaru
 */



/**
* 
* 
*/

/**
 * This class can be used to locate  EJB homes. However at present this class is
 * not being used by WGI module.  Rather SericeLocator being used for this purpose
 */

public class ApplicationStateTrackerHomeLocator {
	private static Logger logger = Logger.getLogger(ApplicationStateTrackerHomeLocator.class);
   /**
    * Cached remote home (EJBHome). Uses lazy loading to obtain its value (loaded by getHome() methods).
   */
   private static RBTApplicationStateTrackerHome cachedRemoteHome = null;

   private static Object lookupHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws NamingException  {
	   javax.naming.InitialContext initialContext = null;
	   try{
           logger.debug("Enviornment:"+ environment);
           logger.debug("JNDIName:"+ jndiName);
		   // Obtain initial context
		   initialContext = new javax.naming.InitialContext(environment);

         Object objRef = initialContext.lookup(jndiName);
         // only narrow if necessary
         if (narrowTo.isInstance(java.rmi.Remote.class))
            return javax.rmi.PortableRemoteObject.narrow(objRef, narrowTo);
         else
            return objRef;
	   } catch (NamingException e) {
		logger.error(e);
		throw e;
	}finally {
         try {
        	 if(initialContext != null){
        		 initialContext.close();
        	 }
		} catch (NamingException e) {
			logger.error(e);
			throw e;
		}
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
