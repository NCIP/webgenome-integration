package gov.nih.nci.caIntegrator.services.appState.dto;

import gov.nih.nci.caIntegrator.services.appState.TrackableAppState;

import java.util.HashMap;
import java.util.List;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 19, 2005
 * Time: 7:41:53 AM
 */
public class RBTReportStateDTO implements TrackableAppState, Serializable {

    // store groupName as String and associatedSamples as String Array
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
