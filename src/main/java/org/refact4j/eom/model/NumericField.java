package org.refact4j.eom.model;

public interface NumericField extends DataField {

    Number getMinValue();

    Number getMaxValue();

    interface NumberFieldVisitor {
        void visitNumberField(NumericField numericField);
    }

}
