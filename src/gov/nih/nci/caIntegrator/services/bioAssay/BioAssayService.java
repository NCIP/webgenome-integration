package gov.nih.nci.caIntegrator.services.bioAssay;

import org.rti.webgenome.client.BioAssayMgr;
import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Aug 29, 2005
 * Time: 10:21:35 AM
 **/
public interface BioAssayService {
    public BioAssayDTO getBioAssay(String bioAssayId, List selectedReporters, String clientID) throws Exception;
    public BioAssayDTO[] getBioAssays(String[] bioAssayIds, BioAssayDataConstraints constraints, List selectedReporters, String clientID) throws Exception;
}
