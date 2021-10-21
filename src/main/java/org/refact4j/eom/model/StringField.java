package org.refact4j.eom.model;

/**
 * A StringField represents a string data field.
 */

public interface StringField extends DataField, StringType {

    interface StringFieldVisitor {
        void visitStringField(StringField stringField);
    }

}
