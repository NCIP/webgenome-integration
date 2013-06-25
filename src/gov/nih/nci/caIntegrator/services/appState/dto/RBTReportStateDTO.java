/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.appState.dto;

import gov.nih.nci.caIntegrator.services.appState.TrackableAppState;

import java.util.HashMap;
import java.util.List;
import java.io.Serializable;

/**
 * @author Ram Bhattaru
 */



/**
* 
* 
*/

public class RBTReportStateDTO implements TrackableAppState, Serializable {

    HashMap groups;
    String userID;
    List selectedReporerNames;
    List cytobands;

    public HashMap getGroups() {
        return groups;
    }

    public void setGroups(HashMap groups) {
        this.groups = groups;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List getSelectedReporerNames() {
        return selectedReporerNames;
    }

    public void setSelectedReporerNames(List selectedReporerNames) {
        this.selectedReporerNames = selectedReporerNames;
    }

    public List getCytobands() {
        return cytobands;
    }

    public void setCytobands(List cytobands) {
        this.cytobands = cytobands;
    }

}
