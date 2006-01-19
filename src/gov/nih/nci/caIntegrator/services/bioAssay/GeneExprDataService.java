package gov.nih.nci.caIntegrator.services.bioAssay;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;

import java.util.List;

/**
 * @author Ram Bhattaru
 */

/**
 * This class is not implemented in this release.  It will be available in next release
 * This class will serve as a strategy by the BioAssayService bean for retrieving the
 * Gene Expression data
*/
public class GeneExprDataService implements BioAssayService{
    public BioAssayDTO getBioAssay(String bioAssayId, List selectedReporters, String clientID) throws Exception {
        return null;
    }

    public BioAssayDTO[] getBioAssays(String[] bioAssayIds, BioAssayDataConstraints constraints, List selectedReporters, String clientID) throws Exception {
        return new BioAssayDTO[0];
    }
}
