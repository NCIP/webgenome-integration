/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.bioAssay.dto;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDatumDTO;

/**
 * @author Ram Bhattaru
 */




/**
* 
* 
*/

public class BioAssayDTOImpl implements BioAssayDTO {
    private String ID;
    private String name;
    private BioAssayDatumDTO[] bioAssayData;
    private String quantitationType;

    public String getQuantitationType() {
        return quantitationType;
    }

    public void setQuantitationType(String quantitationType) {
        this.quantitationType = quantitationType;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public BioAssayDatumDTO[] getBioAssayData() {
        return bioAssayData;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBioAssayData(BioAssayDatumDTO[] bioAssayData) {
        this.bioAssayData = bioAssayData;
    }
}

