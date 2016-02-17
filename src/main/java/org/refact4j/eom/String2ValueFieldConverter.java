package org.refact4j.eom;

import org.refact4j.eom.model.*;

import java.util.StringTokenizer;

public class String2ValueFieldConverter extends DefaultFieldVisitor {
    private Object value;
    private String stringValue;

    public String2ValueFieldConverter(String stringValue) {
        this.stringValue = stringValue;
    }

    public String2ValueFieldConverter() {
    }

    public void visitIntegerField(IntegerField integerField) {
        value = convert(arg -> (!stringValue.equals("") ? new Integer(stringValue) : null));
    }

    public void visitDoubleField(DoubleField doubleField) {
        value = convert(arg -> (!stringValue.equals("") ? new Double(stringValue) : null));
    }

    public void visitStringField(StringField stringField) {
        value = convert(arg -> stringValue);
    }

    public void visitDateField(final DateField dateField) {
        value = convert(arg -> {
            boolean isNullString = stringValue.equals("");
            if (!isNullString) {
                if (dateField != null && dateField.isTimestamp()) {
                    return EntityUtils.parseTimestamp(stringValue);
                }
                return EntityUtils.parseDate(stringValue);
            }
            return null;
        });
    }

    public void visitBooleanField(BooleanField booleanField) {
        value = convert(arg -> "true".equalsIgnoreCase(stringValue) ? Boolean.TRUE : Boolean.FALSE);
    }

    public void visitManyToOneRelationField(ManyToOneRelationField manyToOneRelationField) {
        visitToOne(manyToOneRelationField);
    }

    public void visitOneToOneRelationField(OneToOneRelationField oneToOneRelationField) {
        visitToOne(oneToOneRelationField);
    }

    private void visitToOne(RelationField relationField) {
        if (isStringValueNull(stringValue))
            return;

        KeyBuilder keyBuilder = KeyBuilder.init(relationField.getTargetEntityDescriptor());
        if (this.stringValue != null

                && this.stringValue.startsWith(relationField.getTargetEntityDescriptor().getName() + "[")) {
            for (final Field field : relationField.getTargetEntityDescriptor().getKeyFields()) {

                String keyValues = this.stringValue.substring(this.stringValue.indexOf("[") + 1, this.stringValue
                        .length() - 1);
                StringTokenizer parser = new StringTokenizer(keyValues, ",");
                while (parser.hasMoreTokens()) {
                    String keyValue = parser.nextToken();
                    String key = keyValue.substring(0, keyValue.indexOf("="));
                    String sValue = keyValue.substring(keyValue.indexOf("=") + 1, keyValue.length());
                    Field currentField = relationField.getTargetEntityDescriptor().getField(key);
                    keyBuilder.set(currentField, ConverterHelper.convertString2Value(sValue, currentField));
                }
            }
        } else {
            Field field = relationField.getTargetEntityDescriptor().getKeyFields().get(0);
            field.accept(this);
            keyBuilder.set(field, this.getValue());
        }

        value = keyBuilder.get();
    }


    public Object getValue() {
        return this.value;
    }

    private boolean isStringValueNull(String stringValue) {
        return (stringValue == null || "null".equalsIgnoreCase(stringValue));
    }

    private Object convert(java.util.function.Function<String, Object> functor) {
        if (isStringValueNull(stringValue)) {
            return null;
        }
        return functor.apply(stringValue);
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
        this.value = null;
    }
}
