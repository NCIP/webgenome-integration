package org.rti.webgenome.client;


import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 25, 2005
 * Time: 5:55:03 AM
 */
public interface BioAssayMgr extends Remote{
    public ExperimentDTO getExperiment(String experimentID, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws RemoteException, Exception;
    public ExperimentDTO[] getExperiments(String[] experimentIDs, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws RemoteException, Exception;
}
