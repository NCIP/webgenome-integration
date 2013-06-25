/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.bioAssay;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;

import java.util.List;

/**
 * @author Ram Bhattaru
 */



/**
* 
* 
*/

public interface BioAssayService {
    /**
     * This method retrieves BioAssay data for bioAssay ID passed in.  It will also mark the selected
     *  based on the parameters passed in
     * @param bioAssayId ID of the BioAssay to be retrieved
     * @param selectedReporters Selected reporters in the application (Rembrandt Report)
     * @param clientID Used for retrieving earlier saved application state
     * @return Returns the retrieved BioAssay data as BioAssayDTO
     * @throws Exception
    */
   // public BioAssayDTO getBioAssay(String bioAssayId, List selectedReporters, String clientID) throws Exception;


    /**
     *  This method retrieves BioAssay data for bioAssay IDs passed in.  It will also mark the selected
     *  based on the parameters passed in
     * @param bioAssayIds IDs of the BioAssays to be retrieved
     * @param constraints Search criteria for BioAssays to be retrieved
     * @param selectedReporters Selected reporters in the application (Rembrandt Report)
     * @param clientID Used for retrieving earlier saved application state
     * @return Returns the retrieved BioAssay data as an array of BioAssayDTO
     * @throws Exception
     */
    public BioAssayDTO[] getBioAssays(String[] bioAssayIds, BioAssayDataConstraints constraints, List selectedReporters, String clientID) throws Exception;

}
