package gov.nih.nci.caIntegrator.services.demo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Sep 13, 2005
 * Time: 5:12:12 AM
 */
public interface QuantitationType extends Serializable {
    public final static QuantitationType COPY_NUMBER = new QuantitationTypeImpl();
    public final static QuantitationType COPY_NUMBER_LOG2_RATION = new QuantitationTypeImpl();
    public final static QuantitationType LOH = new QuantitationTypeImpl();
    public final static QuantitationType FOLD_CHANGE = new QuantitationTypeImpl();
    public final static QuantitationType FOLD_CHANGE_LOG2_RATIO = new QuantitationTypeImpl();
    static class QuantitationTypeImpl implements QuantitationType {}
    
}
