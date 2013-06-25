/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package org.rti.webgenome.client;

import java.io.Serializable;

/**
 * @author Ram Bhattaru
 */




/**
* 
* 
*/

/**
 * This interface contains methods to access the Reporter data which includes annotations
 */

public interface ReporterDTO extends Serializable {
    public String getName();
    public String getChromosome();
    public Long getChromosomeLocation();
    public String[] getAssociatedGenes();
    public String[] getAnnotations();
    public Boolean isSelected();
}
