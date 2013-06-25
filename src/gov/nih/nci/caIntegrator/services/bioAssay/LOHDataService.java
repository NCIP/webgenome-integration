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

import java.util.*;

import gov.nih.nci.rembrandt.dto.query.ComparativeGenomicQuery;
import gov.nih.nci.rembrandt.queryservice.resultset.ResultSet;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.QueryProcessor;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.cgh.CopyNumber;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.BioAssayDTOImpl;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.BioAssayDatumDTOImpl;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.ReporterDTOImpl;

/**
 * @author Ram Bhattaru
 */




/**
* 
* 
*/

/**
 * This class is not implemented in this release.  It will be available in next release
 * This class will serve as a strategy by the BioAssayService bean for retrieving the
 * LOH data
*/

public class LOHDataService extends GenomicDataService {

    private static org.apache.log4j.Logger logger_ =
        org.apache.log4j.Logger.getLogger(LOHDataService.class);

    protected String getQuantitationType() {
        return QuantitationTypes.LOH;
    }

    protected Double getDatumValue(CopyNumber cpObj) {
        return cpObj.getLoh();
    }
}
