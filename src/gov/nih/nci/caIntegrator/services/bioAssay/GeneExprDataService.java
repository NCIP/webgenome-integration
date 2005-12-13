package gov.nih.nci.caIntegrator.services.bioAssay;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;
import gov.nih.nci.caIntegrator.services.bioAssay.BioAssayService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 29, 2005
 * Time: 10:24:03 AM
 */
public class GeneExprDataService implements BioAssayService{
    public BioAssayDTO getBioAssay(String bioAssayId, List selectedReporters, String clientID) throws Exception {
        return null;
    }

    public BioAssayDTO[] getBioAssays(String[] bioAssayIds, BioAssayDataConstraints constraints, List selectedReporters, String clientID) throws Exception {
        return new BioAssayDTO[0];
    }
}
