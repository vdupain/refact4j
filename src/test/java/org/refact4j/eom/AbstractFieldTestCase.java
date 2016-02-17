package org.refact4j.eom;

import org.refact4j.eom.model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public abstract class AbstractFieldTestCase {
    final EntityDescriptorBuilder entityDescriptorBuilder = EntityDescriptorBuilder
            .init("type");

    void checkError(Field field, Object value, String message) {

        try {
            EntityObjectBuilder.init(entityDescriptorBuilder.get()).set(
                    field, value).get().checkConstraint();
            fail("Expected Exception");
        } catch (Exception e) {
            assertEquals(message, e.getMessage());
        }
    }

    void checkFieldValue(Field field, Object value, String message) {
        try {
            field.checkValue(value);
            fail("Expected Exception");
        } catch (NullFieldValueNotAllowedException e) {
            assertEquals(field, e.getField());
        } catch (InvalidFieldValueException e) {
            assertEquals(field, e.getField());
            assertEquals(message, e.getMessage());
        }
    }

}
