package gov.nih.nci.caIntegrator.services.appState;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;
import java.security.SecureRandom;

/**
 * @author: Ram Bhattaru with credits to sg & lrs
 * @Date: Sep 16, 2005
 */

/**
* caIntegrator License
*
* Copyright 2001-2005 Science Applications International Corporation ("SAIC").
* The software subject to this notice and license includes both human readable source code form and machine readable,
* binary, object code form ("the caIntegrator Software"). The caIntegrator Software was developed in conjunction with
* the National Cancer Institute ("NCI") by NCI employees and employees of SAIC.
* To the extent government employees are authors, any rights in such works shall be subject to Title 17 of the United States
* Code, section 105.
* This caIntegrator Software License (the "License") is between NCI and You. "You (or "Your") shall mean a person or an
* entity, and all other entities that control, are controlled by, or are under common control with the entity. "Control"
* for purposes of this definition means (i) the direct or indirect power to cause the direction or management of such entity,
*  whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii)
* beneficial ownership of such entity.
* This License is granted provided that You agree to the conditions described below. NCI grants You a non-exclusive,
* worldwide, perpetual, fully-paid-up, no-charge, irrevocable, transferable and royalty-free right and license in its rights
* in the caIntegrator Software to (i) use, install, access, operate, execute, copy, modify, translate, market, publicly
* display, publicly perform, and prepare derivative works of the caIntegrator Software; (ii) distribute and have distributed
* to and by third parties the caIntegrator Software and any modifications and derivative works thereof;
* and (iii) sublicense the foregoing rights set out in (i) and (ii) to third parties, including the right to license such
* rights to further third parties. For sake of clarity, and not by way of limitation, NCI shall have no right of accounting
* or right of payment from You or Your sublicensees for the rights granted under this License. This License is granted at no
* charge to You.
* 1. Your redistributions of the source code for the Software must retain the above copyright notice, this list of conditions
*    and the disclaimer and limitation of liability of Article 6, below. Your redistributions in object code form must reproduce
*    the above copyright notice, this list of conditions and the disclaimer of Article 6 in the documentation and/or other materials
*    provided with the distribution, if any.
* 2. Your end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This
*    product includes software developed by SAIC and the National Cancer Institute." If You do not include such end-user
*    documentation, You shall include this acknowledgment in the Software itself, wherever such third-party acknowledgments
*    normally appear.
* 3. You may not use the names "The National Cancer Institute", "NCI" "Science Applications International Corporation" and
*    "SAIC" to endorse or promote products derived from this Software. This License does not authorize You to use any
*    trademarks, service marks, trade names, logos or product names of either NCI or SAIC, except as required to comply with
*    the terms of this License.
* 4. For sake of clarity, and not by way of limitation, You may incorporate this Software into Your proprietary programs and
*    into any third party proprietary programs. However, if You incorporate the Software into third party proprietary
*    programs, You agree that You are solely responsible for obtaining any permission from such third parties required to
*    incorporate the Software into such third party proprietary programs and for informing Your sublicensees, including
*    without limitation Your end-users, of their obligation to secure any required permissions from such third parties
*    before incorporating the Software into such third party proprietary software programs. In the event that You fail
*    to obtain such permissions, You agree to indemnify NCI for any claims against NCI by such third parties, except to
*    the extent prohibited by law, resulting from Your failure to obtain such permissions.
* 5. For sake of clarity, and not by way of limitation, You may add Your own copyright statement to Your modifications and
*    to the derivative works, and You may provide additional or different license terms and conditions in Your sublicenses
*    of modifications of the Software, or any derivative works of the Software as a whole, provided Your use, reproduction,
*    and distribution of the Work otherwise complies with the conditions stated in this License.
* 6. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO,
*    THE IMPLIED WARRANTIES OF MERCHANTABILITY, NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.
*    IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT,
*    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
*    GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
*    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
*    OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*/

/*  This class will provide services to publish and retrieve TrackableAppState objects.
    Any application which is interested in saving its state (to be queried later by other apps)
    can implement TrackableAppState interface and utilize the services of this class to publish
    and retrieve its state.  These service are exposed utlizing EJB Sessionbeans
*/
final public class ApplicationStateService {

    // may be read this from peroperty file
    private static long APP_STATE_EXPIRE_INTERVAL = 10000000; // milli-secs
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
