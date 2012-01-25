package org.refact4j.eom.model;

/**
 * A Boolean Field.
 */
public interface BooleanField extends DataField, BooleanType {

    interface BooleanFieldVisitor {
        void visitBooleanField(BooleanField booleanField);
    }
}
