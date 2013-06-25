/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package gov.nih.nci.caIntegrator.services.demo;

/**
 * @author Ram Bhattaru
 */




/**
* 
* 
*/

/**
 *  This interface is only for demo purpose and not being used by the actual WGI integration module.
 *  This interface serves as a simple demo for retrieving Copy Number data from caIntegrator
 *  data repository using Rembrandt Query service.
*/
public interface CopyNumberMgr extends javax.ejb.EJBObject {
     /**
     * This mehod should retrieve Copy Number Fact objects using Rembrandt's Query
     * object model.  It constructs the query, exceutes and formats the results
     * @param geneSymbol serves as Search criteria for copy number fact objects
     * @param type This will be one of the Quantitation types (such as Copy Number)
     * @return Returns copy number object data concatenated as a String
     */
    public String getCNData(String geneSymbol, QuantitationType type) throws java.rmi.RemoteException ;
}
