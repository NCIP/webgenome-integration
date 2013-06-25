/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/webgenome-integration/LICENSE.txt for details.
 */

package org.rti.webgenome.client;

/**
 * @author Ram Bhattaru
 */





/**
 * This interface defines all available Quantitation types.  However, only Copy Number
 * type is the only type supported in this release.
 */

public interface QuantitationTypes {
    public final static String COPY_NUMBER = "copyNumber";
    public final static String COPY_NUMBER_LOG2_RATION = "copyNumberLog2Ratio";
    public final static String LOH = "loh";
    public final static String FOLD_CHANGE = "foldChange";
    public final static String FOLD_CHANGE_LOG2_RATIO = "foldChangeLog2Ration";
}
