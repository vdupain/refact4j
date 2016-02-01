package org.refact4j.eom;

import org.refact4j.eom.model.Field;

/**
 * ConverterHelper is an helper class for converting value to string and string
 * to value depending on the type of the field.
 */
public final class ConverterHelper {

    static Value2StringFieldConverter value2StringConverter = new Value2StringFieldConverter();
    static String2ValueFieldConverter string2ValueConverter = new String2ValueFieldConverter();
    private ConverterHelper() {
    }

    public static String convertValue2String(final Object value, final Field field) {
        value2StringConverter.setValue(value);
        field.accept(value2StringConverter);
        return value2StringConverter.getStringValue();
    }

    public static Object convertString2Value(final String stringValue, final Field field) {
        string2ValueConverter.setStringValue(stringValue);
        field.accept(string2ValueConverter);
        return string2ValueConverter.getValue();
    }

}
