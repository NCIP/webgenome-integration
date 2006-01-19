package org.rti.webgenome.client;

import java.io.Serializable;

/**
 * @author Ram Bhattaru
 */

/**
 * This interface contains methods to access the Reporter data for the BioAssay
 */
public interface BioAssayDatumDTO extends Serializable {
    public Double getValue();
    public String getQuantitationType();
    public ReporterDTO getReporter();
}
