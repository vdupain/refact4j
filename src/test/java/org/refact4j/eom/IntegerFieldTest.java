package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.model.IntegerField;


public class IntegerFieldTest extends AbstractFieldTestCase {

    @Test
    public void testNullableField() {
        IntegerField field = FieldFactory.init(entityDescriptorBuilder, "id")
                .setNullable(false).createIntegerField();

        checkFieldValue(field, null, null);
    }

}
