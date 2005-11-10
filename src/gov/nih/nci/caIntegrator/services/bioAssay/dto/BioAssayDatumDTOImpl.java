package gov.nih.nci.caIntegrator.services.bioAssay.dto;

import org.rti.webgenome.client.BioAssayDatumDTO;
import org.rti.webgenome.client.ReporterDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 15, 2005
 * Time: 3:42:03 AM
 */
public class BioAssayDatumDTOImpl implements BioAssayDatumDTO {
    private Double value;
    private String quantitationType;
    private ReporterDTO reporter;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getQuantitationType() {
        return quantitationType;
    }

    public void setQuantitationType(String quantitationType) {
        this.quantitationType = quantitationType;
    }

    public ReporterDTO getReporter() {
        return reporter;
    }

    public void setReporter(ReporterDTO reporter) {
        this.reporter = reporter;
    }
}
