package org.refact4j.eom;

import org.refact4j.eom.model.Field;

/**
 * ConverterHelper is an helper class for converting value to string and string
 * to value depending on the type of the field.
 */
public final class ConverterHelper {

    private ConverterHelper() {
    }

    public static String convertValue2String(final Object value, final Field field) {
        Value2StringFieldConverter converter = new Value2StringFieldConverter();
        converter.setValue(value);
        field.accept(converter);
        return converter.getStringValue();
    }

    public static Object convertString2Value(final String stringValue, final Field field) {
        String2ValueFieldConverter converter = new String2ValueFieldConverter();
        converter.setStringValue(stringValue);
        field.accept(converter);
        return converter.getValue();
    }

}
