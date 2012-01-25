package org.refact4j.eom.model;

@SuppressWarnings("serial")
public class InvalidFieldValueException extends RuntimeException {

    private final Field field;

    InvalidFieldValueException(Field field) {
        super("Invalid Field Value : " + field.getName());
        this.field = field;
    }

    public InvalidFieldValueException(Field field, String message) {
        super(message);
        this.field = field;
    }

    public Field getField() {
        return field;
    }

}
