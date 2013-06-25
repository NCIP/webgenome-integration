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
 * This interface contains methods to access BioAssayDatums of BioAssay
 */

public interface BioAssayDTO extends Serializable {
    public String getID();
    public String getName();
    public String getQuantitationType();
    public BioAssayDatumDTO[] getBioAssayData();
}
