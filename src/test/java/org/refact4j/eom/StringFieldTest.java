package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.model.StringField;
import org.refact4j.eom.model.impl.Stringifiers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class StringFieldTest extends AbstractFieldTestCase {

    @Test
    public void testCheckValues() throws Exception {

        final StringField field = FieldFactory.init(entityDescriptorBuilder, "name")
                .createStringField();
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
        StringField field = FieldFactory.init(entityDescriptorBuilder, fieldName).createStringField();
        field.checkValue(null);
        field.checkValue("");
        field.checkValue("0123456789");
    }

}
