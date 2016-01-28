package org.refact4j.eom;

import org.refact4j.eom.model.*;

import java.util.Date;
import java.util.function.Function;

public class Value2StringFieldConverter extends DefaultFieldVisitor {
    private String stringValue;

    private Object value;

    public Value2StringFieldConverter() {
    }

    public Value2StringFieldConverter(Object value) {
        this.value = value;
    }

    public void visitIntegerField(IntegerField integerField) {
        this.stringValue = convert();
    }

    public void visitDoubleField(DoubleField doubleField) {
        this.stringValue = convert();
    }

    public void visitStringField(StringField stringField) {
        this.stringValue = convert();
    }

    public void visitDateField(final DateField dateField) {
        this.stringValue = convert(new Function<Object, String>() {
            public String apply(Object arg) {
                return (dateField != null && dateField.isTimestamp()) ? EntityUtils.formatTimestamp((Date) value)
                        : EntityUtils.formatDate((Date) value);
            }
        });
    }

    public void visitBooleanField(BooleanField booleanField) {
        this.stringValue = convert(new Function<Object, String>() {
            public String apply(Object arg) {
                return (Boolean) arg ? "true" : "false";
            }
        });
    }

    public void visitManyToOneRelationField(ManyToOneRelationField manyToOneRelationField) {
        visitToOne();
    }

    private void visitToOne() {
        this.stringValue = convert(new Function<Object, String>() {
            public String apply(Object arg) {
                Key key = (Key) value;
                try {
                    Object keyValue = EntityUtils.getKeyValue(key);
                    return keyValue != null ? keyValue.toString() : "null";
                } catch (Exception e) {
                    return key.toString();
                }
            }
        });
    }

    public void visitOneToOneRelationField(OneToOneRelationField oneToOneRelationField) {
        visitToOne();
    }

    public String getStringValue() {
        return this.stringValue;
    }

    private String convert() {
        return convert(new Function<Object, String>() {

            public String apply(Object arg) {
                return value.toString();
            }

        });
    }

    private String convert(java.util.function.Function<Object, String> functor) {
        if (value == null) {
            return "null";
        }
        return functor.apply(value);
    }

    public void setValue(Object value) {
        this.value = value;
        this.stringValue = null;
    }
}
