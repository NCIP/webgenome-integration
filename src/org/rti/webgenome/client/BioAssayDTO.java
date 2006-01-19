package org.rti.webgenome.client;

import java.io.Serializable;

/**
 * @author Ram Bhattaru
 */

/**
 * This interface contains methods to access BioAssayDatums of BioAssay 
 */


public interface BioAssayDTO extends Serializable {
    public String getID();
    public String getName();
    public BioAssayDatumDTO[] getBioAssayData();
}
