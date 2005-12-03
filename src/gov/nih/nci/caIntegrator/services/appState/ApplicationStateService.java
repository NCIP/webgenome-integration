package gov.nih.nci.caIntegrator.services.appState;

import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Collection;
import java.security.SecureRandom;

/**
 * Created by IntelliJ IDEA.
 * author: Ram with credits to sg & lrs
 * Date: Sep 16, 2005
 * Time: 5:56:26 AM
 */

/*  This class will provide services to publish and retrieve TrackableAppState objects.
    Any application which is interested in saving its state (to be queried later by other apps)
    can implement TrackableAppState interface and utilize the services of this class to publish
    and retrieve its state.  These service are exposed utlizing EJB Sessionbeans
*/

final public class ApplicationStateService {

    // may be read this from peroperty file
    private static long APP_STATE_EXPIRE_INTERVAL = 500000; // milli-secs
    private final static HashMap activeStates = new HashMap();

    // singleton
    private static ApplicationStateService  ONLY_INSTANCE = new ApplicationStateService();

    /* this will run as Daemon.  It will remove the app states that are expired */
    private static class StateWatchDog implements Runnable {
        public void run() {
              while(true) {
                   try {
                     Thread.sleep(10000);
                     Collection entries = activeStates.values();
                     Iterator statesIter = entries.iterator();
                     while (statesIter.hasNext()) {
                         ApplicationState s =  (ApplicationState)statesIter.next();
                         long now = System.currentTimeMillis();
                         if (now - s.getTimeLastAccessed() > APP_STATE_EXPIRE_INTERVAL) {
                             removeState(s.getID());
                         }
                     }
                   } catch(InterruptedException ie) {
                       // no big deal.  Ignore it
                   }
              }
        }
    }


    final private static void addState(ApplicationState appState) {
        activeStates.put(appState.getID(), appState);
    }

    /* this method returns the application state (as TrackableAppState) for appStateID */
    final public static TrackableAppState getState(Integer appStateID) throws Exception {
        if (activeStates.containsKey(appStateID)) {
            ApplicationState state = (ApplicationState) activeStates.get(appStateID);
            state.setTimeLastAccessed(new Long(System.currentTimeMillis()));
            return state.getState();
        }
        throw new Exception ("No State exists for application state ID: " + appStateID);
    }

    final private static void removeState(Integer appStateID) {
        if (activeStates.containsKey(appStateID)) {
            activeStates.remove(appStateID);
        }
    }

    private ApplicationStateService() {
        Thread t = new Thread(new StateWatchDog());
        t.setDaemon(true);
        t.start();
    }

    public static ApplicationStateService getInstance() {
        return ONLY_INSTANCE;
    }

    synchronized public static Integer getNextID() {
        // TODO:  come up with a better algorithm to generate this ID
        //generate a random number using SecureRandom
        SecureRandom prng = new java.security.SecureRandom();
        Integer id = new Integer(prng.nextInt());
        if (id < 0) id *= -1;
        return id;
    }


    /* this method publishes the application state (passed as TrackableAppState) and returns
       the auto-generated ID under which it was saved.  The client can use this ID to retieve this application
       state (as TrackableAppState) at later point of time before the the predefined time expires
    */
   final public static Integer publishState(TrackableAppState trackableAppState) {
        ApplicationState appState = new ApplicationState(trackableAppState, null, null);
        Integer id = getNextID();

        // if this id is already used, generate a new one
        while (true && activeStates.containsKey(id)) {
            id = getNextID();
        }

        appState.setID(id);
        appState.setTimeLastAccessed(new Long (System.currentTimeMillis()));
        addState(appState);
        return appState.getID();
    }

    private static class ApplicationState {
        TrackableAppState state;
        Integer ID;
        Long timeLastAccessed;

        public ApplicationState(TrackableAppState state, Integer ID, Long timeLastAccessed) {
            this.state = state;
            this.ID = ID;
            this.timeLastAccessed = timeLastAccessed;
        }
        public TrackableAppState  getState() {
            return state;
        }
        public void setStateDTO(TrackableAppState state) {
            this.state = state;
        }
        public Integer getID() {
            return ID;
        }
        public void setID(Integer ID) {
            this.ID = ID;
        }
        public Long getTimeLastAccessed() {
            return timeLastAccessed;
        }
        public void setTimeLastAccessed(Long timeLastAccessed) {
            this.timeLastAccessed = timeLastAccessed;
        }
    }

}
