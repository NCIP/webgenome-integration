/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package org.rti.webgenome.client;


import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 * @author Ram Bhattaru
 */




/**
* 
* 
*/


/**
 * This interface defines the business interface (Remote interface) for serving BioAssay data.
 *  Clients will have to implement this interface and make it available to WebGenome app.  So
 *  that WebGenome app can then use this interface to talk to the clients
 */

public interface BioAssayMgr extends Remote{
    /**
     * This method should provide Experiment (group of BioAssays) data for experiment ID passed in and
     * based on BioAssayDataConstraints specified
     * @param experimentID ID of the BioAssay Experiment to be retrieved
     * @param bioAssayDataConstraints This is the bioAssayConstraints object that contains search criteria for BioAssay
     * @param clientID Used for communicating with the client
     * @return Returns the retrieved Experiment data as ExperimentDTO
     * @throws RemoteException
     * @throws Exception
     */
     public ExperimentDTO getExperiment(String experimentID, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws RemoteException, Exception;

    /**
     * This method should provide Experiments (groups of BioAssays) data for experiment IDs passed in and
     * based on BioAssayDataConstraints specified
     * @param experimentIDs IDs of the BioAssay Experiment to be retrieved
     * @param bioAssayDataConstraints This is the bioAssayConstraints object that contains search criteria for BioAssay
     * @param clientID Used for communicating with the client
     * @return Returns the retrieved Experiments data as an arrray of ExperimentDTOs
     * @throws RemoteException
     * @throws Exception
     */
     public ExperimentDTO[] getExperiments(String[] experimentIDs, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws RemoteException, Exception;
}
