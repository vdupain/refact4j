package org.refact4j.eom.model;

/**
 * A DoubleField represents a double data field.
 */
public interface DoubleField extends NumericField, DoubleType {

    interface DoubleFieldVisitor {
        void visitDoubleField(DoubleField doubleField);
    }
}
