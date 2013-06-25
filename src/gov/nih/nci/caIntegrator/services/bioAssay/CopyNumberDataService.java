/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.bioAssay;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;
import org.rti.webgenome.client.QuantitationTypes;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.BioAssayDTOImpl;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.BioAssayDatumDTOImpl;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.ReporterDTOImpl;
import gov.nih.nci.rembrandt.dto.query.ComparativeGenomicQuery;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.QueryProcessor;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.cgh.CopyNumber;
import gov.nih.nci.rembrandt.queryservice.QueryManager;
import gov.nih.nci.rembrandt.queryservice.resultset.ResultSet;
import gov.nih.nci.caintegrator.dto.query.QueryType;
import gov.nih.nci.caintegrator.dto.critieria.RegionCriteria;
import gov.nih.nci.caintegrator.dto.critieria.SampleCriteria;
import gov.nih.nci.caintegrator.dto.critieria.AssayPlatformCriteria;
import gov.nih.nci.caintegrator.dto.critieria.Constants;
import gov.nih.nci.caintegrator.dto.de.SampleIDDE;
import gov.nih.nci.caintegrator.dto.de.AssayPlatformDE;
import gov.nih.nci.caintegrator.dto.de.ChromosomeNumberDE;
import gov.nih.nci.caintegrator.dto.de.BasePairPositionDE;


import java.util.*;

/**
 * @author Ram Bhattaru
 */





/**
 *  This class will serve as a strategy by the BioAssayService bean for retrieving the
 *  Copy Number data
*/

public class CopyNumberDataService extends GenomicDataService {

    private static org.apache.log4j.Logger logger_ =
        org.apache.log4j.Logger.getLogger(CopyNumberDataService .class);

    protected Double getDatumValue(CopyNumber cpObj) {
        return cpObj.getCopyNumber();  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected String getQuantitationType() {
        return QuantitationTypes.COPY_NUMBER;
    }
    
}