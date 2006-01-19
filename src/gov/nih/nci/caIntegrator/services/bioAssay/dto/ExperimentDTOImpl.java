package gov.nih.nci.caIntegrator.services.bioAssay.dto;

import org.rti.webgenome.client.ExperimentDTO;
import org.rti.webgenome.client.BioAssayDTO;

/**
 * @author Ram Bhattaru
 */


public class ExperimentDTOImpl implements ExperimentDTO {
    private String experimentID;
    private BioAssayDTO[] bioAssays;

    public String getExperimentID() {
        return experimentID;
    }

    public void setExperimentID(String experimentID) {
        this.experimentID = experimentID;
    }

    public BioAssayDTO[] getBioAssays() {
        return bioAssays;
    }

    public void setBioAssays(BioAssayDTO[] bioAssays) {
        this.bioAssays = bioAssays;
    }
}
