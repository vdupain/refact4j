package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.model.BarDesc;
import org.refact4j.model.FooDesc;

import static org.junit.Assert.assertEquals;

public class RelationFieldTest {

    @Test
    public void testRelationshipField() {
        assertEquals(BarDesc.FOOS, FooDesc.BAR.getInverseRelationField());
        assertEquals(FooDesc.BAR, BarDesc.FOOS.getInverseRelationField());
    }

}
