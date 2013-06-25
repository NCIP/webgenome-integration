/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package org.rti.webgenome.client;

import java.io.Serializable;

/**
 * @author Ram Bhattaru
 */



/**
* 
* 
*/

/**
 * This interface contains methods to access the Reporter data for the BioAssay
 */

public interface BioAssayDatumDTO extends Serializable {
    public Double getValue();
   // public String getQuantitationType();
    public ReporterDTO getReporter();
}
