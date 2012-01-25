package org.refact4j.eom.model;

/**
 * A StringField represents a string data field.
 */

public interface StringField extends DataField, StringType {

    Integer getMaxLength();

    Integer getMinLength();

    interface StringFieldVisitor {
        void visitStringField(StringField stringField);
    }

}
