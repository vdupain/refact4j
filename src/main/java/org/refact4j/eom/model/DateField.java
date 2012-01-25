package org.refact4j.eom.model;

/**
 * A DateField represents a date data field.
 */
public interface DateField extends DataField, DateType {

    boolean isTimestamp();

    interface DateFieldVisitor {
        void visitDateField(DateField dateField);
    }
}
