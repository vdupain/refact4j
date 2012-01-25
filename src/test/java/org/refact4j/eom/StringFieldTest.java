package org.refact4j.eom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.model.Property;
import org.refact4j.eom.model.StringField;
import org.refact4j.eom.model.impl.AbstractFieldStringifierFunctor;
import org.refact4j.expr.ExpressionBuilder;


public class StringFieldTest extends AbstractFieldTestCase {

    @Test
    public void testCheckValues() throws Exception {

        final StringField field = FieldFactory.init(entityDescriptorBuilder, "name")
                .createStringField();

        entityDescriptorBuilder.setConstraint(EntityExpressionBuilder.init(field)
                .equalTo("012345").getExpression());

        checkError(field, "0123456789ab",
                "Constraint (type.name=012345) failed: name=0123456789ab");

        assertEquals("name", AbstractFieldStringifierFunctor.PRETTY.stringify(field));
        try {
            AbstractFieldStringifierFunctor.XML.stringify(field);
            fail("Exception Expected");
        } catch (Exception e) {
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
                                .maxLength(10).getExpression()).createStringField();
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

    @Test
    public void testFieldProperty() {
        StringField field = FieldFactory.init(entityDescriptorBuilder, "name")
                .addProperty("key1", "value1").addProperty("key2", "value2")
                .createStringField();
        Property property = field.getProperty();
        assertEquals("value1", property.getProperty("key1"));
        assertEquals("value1", field.getProperty("key1"));
        assertEquals("value2", property.getProperty("key2"));
        assertEquals("value2", field.getProperty("key2"));
        assertEquals(null, field.getProperty("???"));
        assertEquals(property, property.getProperty());

        field.addProperty("key3", "value3");
        assertEquals("value3", property.getProperty("key3"));
        assertEquals("value3", field.getProperty("key3"));
    }

    @Test
    public void testFieldPropertyWithDoublonKeys() {
        try {
            FieldFactory.init(entityDescriptorBuilder, "name").addProperty("key1",
                    "value").addProperty("key1", "value2").createStringField();
            fail("RuntimeException Expected");
        } catch (Exception e) {
            assertEquals("Property with key 'key1' already exists", e
                    .getMessage());
        }
    }

}
