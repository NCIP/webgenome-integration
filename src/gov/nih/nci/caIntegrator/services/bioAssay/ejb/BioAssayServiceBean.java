package gov.nih.nci.caIntegrator.services.bioAssay.ejb;

import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTracker;
import gov.nih.nci.caIntegrator.services.appState.ApplicationStateTrackerHome;
import gov.nih.nci.caIntegrator.services.appState.dto.RBTReportStateDTO;
import gov.nih.nci.caIntegrator.services.appState.ejb.RBTApplicationStateTrackerHome;
import gov.nih.nci.caIntegrator.services.bioAssay.BioAssayService;
import gov.nih.nci.caIntegrator.services.bioAssay.CopyNumberDataService;
import gov.nih.nci.caIntegrator.services.bioAssay.GeneExprDataService;
import gov.nih.nci.caIntegrator.services.bioAssay.LOHDataService;
import gov.nih.nci.caIntegrator.services.bioAssay.dto.ExperimentDTOImpl;
import gov.nih.nci.caIntegrator.services.util.ServiceLocator;
import gov.nih.nci.rembrandt.util.WGIContext;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.rti.webgenome.client.BioAssayDTO;
import org.rti.webgenome.client.BioAssayDataConstraints;
import org.rti.webgenome.client.BioAssayMgr;
import org.rti.webgenome.client.ExperimentDTO;
import org.rti.webgenome.client.QuantitationTypes;

/**
 * @author Ram Bhattaru
 */


/**
* caIntegrator License
*
* Copyright 2001-2005 Science Applications International Corporation ("SAIC").
* The software subject to this notice and license includes both human readable source code form and machine readable,
* binary, object code form ("the caIntegrator Software"). The caIntegrator Software was developed in conjunction with
* the National Cancer Institute ("NCI") by NCI employees and employees of SAIC.
* To the extent government employees are authors, any rights in such works shall be subject to Title 17 of the United States
* Code, section 105.
* This caIntegrator Software License (the "License") is between NCI and You. "You (or "Your") shall mean a person or an
* entity, and all other entities that control, are controlled by, or are under common control with the entity. "Control"
* for purposes of this definition means (i) the direct or indirect power to cause the direction or management of such entity,
*  whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii)
* beneficial ownership of such entity.
* This License is granted provided that You agree to the conditions described below. NCI grants You a non-exclusive,
* worldwide, perpetual, fully-paid-up, no-charge, irrevocable, transferable and royalty-free right and license in its rights
* in the caIntegrator Software to (i) use, install, access, operate, execute, copy, modify, translate, market, publicly
* display, publicly perform, and prepare derivative works of the caIntegrator Software; (ii) distribute and have distributed
* to and by third parties the caIntegrator Software and any modifications and derivative works thereof;
* and (iii) sublicense the foregoing rights set out in (i) and (ii) to third parties, including the right to license such
* rights to further third parties. For sake of clarity, and not by way of limitation, NCI shall have no right of accounting
* or right of payment from You or Your sublicensees for the rights granted under this License. This License is granted at no
* charge to You.
* 1. Your redistributions of the source code for the Software must retain the above copyright notice, this list of conditions
*    and the disclaimer and limitation of liability of Article 6, below. Your redistributions in object code form must reproduce
*    the above copyright notice, this list of conditions and the disclaimer of Article 6 in the documentation and/or other materials
*    provided with the distribution, if any.
* 2. Your end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This
*    product includes software developed by SAIC and the National Cancer Institute." If You do not include such end-user
*    documentation, You shall include this acknowledgment in the Software itself, wherever such third-party acknowledgments
*    normally appear.
* 3. You may not use the names "The National Cancer Institute", "NCI" "Science Applications International Corporation" and
*    "SAIC" to endorse or promote products derived from this Software. This License does not authorize You to use any
*    trademarks, service marks, trade names, logos or product names of either NCI or SAIC, except as required to comply with
*    the terms of this License.
* 4. For sake of clarity, and not by way of limitation, You may incorporate this Software into Your proprietary programs and
*    into any third party proprietary programs. However, if You incorporate the Software into third party proprietary
*    programs, You agree that You are solely responsible for obtaining any permission from such third parties required to
*    incorporate the Software into such third party proprietary programs and for informing Your sublicensees, including
*    without limitation Your end-users, of their obligation to secure any required permissions from such third parties
*    before incorporating the Software into such third party proprietary software programs. In the event that You fail
*    to obtain such permissions, You agree to indemnify NCI for any claims against NCI by such third parties, except to
*    the extent prohibited by law, resulting from Your failure to obtain such permissions.
* 5. For sake of clarity, and not by way of limitation, You may add Your own copyright statement to Your modifications and
*    to the derivative works, and You may provide additional or different license terms and conditions in Your sublicenses
*    of modifications of the Software, or any derivative works of the Software as a whole, provided Your use, reproduction,
*    and distribution of the Work otherwise complies with the conditions stated in this License.
* 6. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO,
*    THE IMPLIED WARRANTIES OF MERCHANTABILITY, NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.
*    IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT,
*    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
*    GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
*    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
*    OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*/

/**
 * This class is the EJB implementation of BioAssayMgr business interface.
 */

public class BioAssayServiceBean extends AbstractServiceBean implements BioAssayMgr {
    private BioAssayService service;


    private static org.apache.log4j.Logger logger_ =
        org.apache.log4j.Logger.getLogger(BioAssayServiceBean.class);

    public void ejbCreate() throws CreateException {
        logger_.debug("ejbCreate()");
        WGIContext.init();
        logger_.debug("DATA SOURCE DETAILS: ");
        logger_.debug(System.getProperty("gov.nih.nci.wgi.dbalias"));
        logger_.debug(System.getProperty("gov.nih.nci.wgi.jcd_alias"));
    }

    public void ejbRemove() throws javax.ejb.EJBException{
        logger_.debug("ejbRemove()");
    }

    private void createServiceType(String serviceType) throws Exception {
            if (serviceType.equals(QuantitationTypes.COPY_NUMBER))
                service = new CopyNumberDataService();
            else if (serviceType.equals(QuantitationTypes.LOH))
                service = new LOHDataService();
            else if (serviceType.equals(QuantitationTypes.FOLD_CHANGE))
                service = new GeneExprDataService();
            else throw new Exception("Measurement Not Supported: " + serviceType);
    }

    /**
     * This method retrieves Experiment (group of BioAssays) for experiment ID passed in and
     * based on BioAssayDataConstraints specified
     * @param experimentID ID of the BioAssay Experiment to be retrieved
     * @param assayDataConstraints This is the bioAssayConstraints object that contains search criteria for BioAssay
     * @param clientID Used for retrieving earlier saved application state
     * @return Returns the retrieved Experiment data as ExperimentDTO
     * @throws Exception
     */
    public ExperimentDTO getExperiment(String experimentID, BioAssayDataConstraints assayDataConstraints, String clientID) throws Exception {

        logger_.debug("\n\n\n\n*********** BEGIN  NEW EXPERIMENT (" + experimentID +
                ") REQUEST FOR: " + clientID + "********");

        String message = "Received Request for experiment ID:{0} from client ID: {1}";
        String logMessage = MessageFormat.format(message, new Object[] {experimentID, clientID});
        logger_.debug(logMessage);

        String serviceType = assayDataConstraints.getQuantitationType();
        createServiceType(serviceType);
        assert (service != null);

        // retrieve the report state that got saved earlier for this clientID
        ApplicationStateTracker stateService = getAppStateTrackerService();
        RBTReportStateDTO state = null;
        try {
            Integer stateID = new Integer(clientID);
            state = (RBTReportStateDTO) stateService.getReportState(stateID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            /* this means the clientID passed was illegal string.  It should purely be a number as that
               is what this service creates and returns when the state was saved earlier */
            throw new Exception ("No State exists for application state ID: " + clientID);
        }

        assert(state != null);

        HashMap groups = state.getGroups();
        String[] bioAssayIDS = (String[]) groups.get(experimentID);
        List reporterNames = state.getSelectedReporerNames();
        BioAssayDTO[] bioAssays = service.getBioAssays(bioAssayIDS, assayDataConstraints, reporterNames, clientID);

        String assayMessage = "TOTAL BIOASSAYS RETURNED: {0} FOR CLIENT ID: {1}:";
        String assayLogMessage = MessageFormat.format(assayMessage, new Object[] {bioAssays.length, clientID});
        logger_.debug(assayLogMessage);

        ExperimentDTOImpl exptDTO = new ExperimentDTOImpl();
        exptDTO.setExperimentID(experimentID);
        exptDTO.setBioAssays(bioAssays);

        logger_.debug("\n\n************ END EXPERIMENT (" + experimentID +
                ") REQUEST FOR: " + clientID + "*******\n");


        return exptDTO;
    }

    private ApplicationStateTracker getAppStateTrackerService() throws Exception {
        try {
             ServiceLocator locator = ServiceLocator.getInstance();
             Object h = locator.locateHome(null, RBTApplicationStateTrackerHome.JNDI_NAME,
                                             ApplicationStateTrackerHome.class);
             ApplicationStateTrackerHome home = (ApplicationStateTrackerHome)h;
             ApplicationStateTracker  service = home.create();
             return service;
        } catch (NamingException ne) {
             ne.printStackTrace();
             throw new Exception(ne);
         } catch (CreateException ce) {
             ce.printStackTrace();
             throw  new Exception(ce);
         }
    }

    /**
     * This method retrieves Experiments (groups of BioAssays) for experiment IDs passed in and
     * based on BioAssayDataConstraints specified
     *
     * @param experimentIDs IDs of the BioAssay Experiments to be retrieved
     * @param bioAssayDataConstraints This is the bioAssayConstraints object that contains search criteria for BioAssay
     * @param clientID Used for retrieving earlier saved application state
     * @return Returns the retrieved Experiment data as an array of ExperimentDTOs
     * @throws Exception
     */
    public ExperimentDTO[] getExperiments(String[] experimentIDs, BioAssayDataConstraints bioAssayDataConstraints, String clientID) throws Exception {
        ExperimentDTO[] resultantDTOs = new ExperimentDTO[experimentIDs.length];
        for (int i = 0; i < experimentIDs.length; i++) {
            String experimentID = experimentIDs[i];
            resultantDTOs[i] = getExperiment(experimentID, bioAssayDataConstraints, clientID );
        }
        return resultantDTOs;
    }
}
