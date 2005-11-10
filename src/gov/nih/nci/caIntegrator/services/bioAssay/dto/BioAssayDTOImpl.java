package gov.nih.nci.caIntegrator.services.bioAssay.dto;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDatumDTO;

import java.io.Serializable;
import java.rmi.server.RemoteObject;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 15, 2005
 * Time: 3:34:34 AM
 */
public class BioAssayDTOImpl implements BioAssayDTO {
    private String ID;
    private String name;
    private BioAssayDatumDTO[] bioAssayData;  // comment

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

