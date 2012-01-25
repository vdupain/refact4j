package org.refact4j.eom.model;

@SuppressWarnings("serial")
public class NullFieldValueNotAllowedException extends InvalidFieldValueException {

    public NullFieldValueNotAllowedException(Field field) {
        super(field);
    }

}
