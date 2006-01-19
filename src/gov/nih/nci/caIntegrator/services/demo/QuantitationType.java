package gov.nih.nci.caIntegrator.services.demo;

import java.io.Serializable;

/**
 * @author Ram Bhattaru
 */

/**
 * This interface is only for demo purpose and not being used by the actual WGI integration module.
*/

public interface QuantitationType extends Serializable {
    public final static QuantitationType COPY_NUMBER = new QuantitationTypeImpl();
    public final static QuantitationType COPY_NUMBER_LOG2_RATION = new QuantitationTypeImpl();
    public final static QuantitationType LOH = new QuantitationTypeImpl();
    public final static QuantitationType FOLD_CHANGE = new QuantitationTypeImpl();
    public final static QuantitationType FOLD_CHANGE_LOG2_RATIO = new QuantitationTypeImpl();
    static class QuantitationTypeImpl implements QuantitationType {}
}
