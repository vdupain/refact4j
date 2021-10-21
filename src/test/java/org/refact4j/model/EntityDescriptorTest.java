package org.refact4j.model;

import org.junit.Test;
import org.refact4j.eom.model.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class EntityDescriptorTest {

    @Test
    public void testDummyEntityDescriptor() {
        for (Field field : FooDesc.INSTANCE.getFields()) {
            assertSame(field.getEntityDescriptor(), FooDesc.INSTANCE);
        }
        for (Field field : FooDesc.INSTANCE.getKeyFields()) {
            assertSame(FooDesc.ID, field);
        }
        assertSame(FooDesc.INSTANCE.getField(FooDesc.ID.getName()), FooDesc.ID);
        assertSame(FooDesc.INSTANCE.getField(FooDesc.NAME.getName()),
                FooDesc.NAME);
        assertSame(FooDesc.INSTANCE.getField(FooDesc.VALUE.getName()),
                FooDesc.VALUE);
        assertEquals(FooDesc.NAME.getDefaultValue(), "");
    }

}
