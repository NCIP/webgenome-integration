package gov.nih.nci.caIntegrator.services.bioAssay.dto;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDatumDTO;

/**
 * @author Ram Bhattaru
 */


public class BioAssayDTOImpl implements BioAssayDTO {
    private String ID;
    private String name;
    private BioAssayDatumDTO[] bioAssayData;

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

