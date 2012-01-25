package org.refact4j.eom.model;

/**
 * An IntegerField represents an integer data field.
 */

public interface IntegerField extends NumericField, IntegerType {

    interface IntegerFieldVisitor {
        void visitIntegerField(IntegerField integerField);
    }

}
