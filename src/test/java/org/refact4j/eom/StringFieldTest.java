package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.model.StringField;
import org.refact4j.eom.model.impl.Stringifiers;
import org.refact4j.expr.ExpressionBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class StringFieldTest extends AbstractFieldTestCase {

    @Test
    public void testCheckValues() throws Exception {

        final StringField field = FieldFactory.init(entityDescriptorBuilder, "name")
                .createStringField();

        entityDescriptorBuilder.setConstraint(ExpressionBuilder.init(field)
                .equalTo("012345").get());

        checkError(field, "0123456789ab",
                "Constraint (type.name=012345) failed: name=0123456789ab");

        assertEquals("name", Stringifiers.FIELD_PRETTY.stringify(field));
        try {
            Stringifiers.FIELD_XML.stringify(field);
            fail("Exception Expected");
        } catch (Exception ignored) {
        }

    }

    @Test
    public void testNullableField() {
        StringField field = FieldFactory.init(entityDescriptorBuilder, "name")
                .setNullable(false).createStringField();

        checkFieldValue(field, null, null);

        field = FieldFactory.init(entityDescriptorBuilder, "name").setNullable(true)
                .createStringField();
        field.checkValue(null);
    }

    @Test
    public void testFieldConstraint() {
        String fieldName = "name";
        StringField field = FieldFactory.init(entityDescriptorBuilder, fieldName)
                .setConstraintExpression(
                        ExpressionBuilder.init(fieldName)
                                .maxLength(10).get()).createStringField();
        field.checkValue(null);
        field.checkValue("");
        field.checkValue("0123456789");
        checkFieldValue(field, "0123456789ab", "(name.length<=10)");
    }

    @Test
    public void testMinMaxLengthFieldConstraint() {
        StringField field = FieldFactory.init(entityDescriptorBuilder, "name")
                .setMaxLength(10).createStringField();
        field.checkValue(null);
        field.checkValue("");
        field.checkValue("1234567890");
        checkFieldValue(field, "12345678901", "(name.length<=10)");

        field = FieldFactory.init(entityDescriptorBuilder, "name").setMinLength(5)
                .createStringField();
        field.checkValue("12345678901");
        field.checkValue("12345");
        checkFieldValue(field, null, "(name.length>=5)");
        checkFieldValue(field, "", "(name.length>=5)");
        checkFieldValue(field, "1234", "(name.length>=5)");

        field = FieldFactory.init(entityDescriptorBuilder, "name").setMinLength(5)
                .setMaxLength(10).createStringField();
        field.checkValue("12345");
        field.checkValue("1234567890");
        checkFieldValue(field, null, "((name.length>=5) AND (name.length<=10))");
        checkFieldValue(field, "", "((name.length>=5) AND (name.length<=10))");
        checkFieldValue(field, "1234",
                "((name.length>=5) AND (name.length<=10))");
        checkFieldValue(field, "12345678901",
                "((name.length>=5) AND (name.length<=10))");
    }
}
