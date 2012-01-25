package org.refact4j.eom;

import org.refact4j.eom.model.BooleanField;
import org.refact4j.eom.model.DateField;
import org.refact4j.eom.model.DefaultFieldVisitor;
import org.refact4j.eom.model.DoubleField;
import org.refact4j.eom.model.IntegerField;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.ManyToOneRelationField;
import org.refact4j.eom.model.OneToOneRelationField;
import org.refact4j.eom.model.StringField;
import org.refact4j.functor.UnaryFunctor;

import java.util.Date;

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
        this.stringValue = convert(new UnaryFunctor<Object, String>() {
            public String eval(Object arg) {
                return (dateField != null && dateField.isTimestamp()) ? EntityUtils.formatTimestamp((Date) value)
                        : EntityUtils.formatDate((Date) value);
            }
        });
    }

    public void visitBooleanField(BooleanField booleanField) {
        this.stringValue = convert(new UnaryFunctor<Object, String>() {
            public String eval(Object arg) {
                return (Boolean) arg ? "true" : "false";
            }
        });
    }

    public void visitManyToOneRelationField(ManyToOneRelationField manyToOneRelationField) {
        visitToOne();
    }

    private void visitToOne() {
        this.stringValue = convert(new UnaryFunctor<Object, String>() {
            public String eval(Object arg) {
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
        return convert(new UnaryFunctor<Object, String>() {

            public String eval(Object arg) {
                return value.toString();
            }

        });
    }

    private String convert(UnaryFunctor<Object, String> functor) {
        if (value == null) {
            return "null";
        }
        return functor.eval(value);
    }

    public void setValue(Object value) {
        this.value = value;
        this.stringValue = null;
    }
}
