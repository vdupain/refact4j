package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.model.CompoundKeyFooDesc;
import org.refact4j.model.FooDesc;
import org.refact4j.util.EqualsHashCodeAssert;
import org.refact4j.util.HashCodeTestUtils;

import static org.junit.Assert.*;

public class KeyBuilderTest {

    @Test
    public void testEqualsAndHashCodeForFieldsKey() throws Exception {
        Key key_1a = KeyBuilder.init(CompoundKeyFooDesc.INSTANCE).set(CompoundKeyFooDesc.ID, 1).set(
                CompoundKeyFooDesc.NAME, "a").getKey();
        Key key_a1 = KeyBuilder.init(CompoundKeyFooDesc.INSTANCE).set(CompoundKeyFooDesc.NAME, "a").set(
                CompoundKeyFooDesc.ID, 1).getKey();
        Key key_2b = KeyBuilder.init(CompoundKeyFooDesc.INSTANCE).set(CompoundKeyFooDesc.ID, 2).set(
                CompoundKeyFooDesc.NAME, "b").getKey();
        HashCodeTestUtils.checkEqualsAndHashCode(key_1a, key_a1, key_2b);
        checkComparison(key_1a, key_2b, -1);
        checkComparison(key_1a, key_1a, 0);
        checkComparison(key_2b, key_1a, 1);

        EqualsHashCodeAssert.assertEqualsHashCodeCoherent(key_1a, key_1a);
        EqualsHashCodeAssert.assertEqualsIsSymmetric(key_1a, key_1a);
        assertNotNull(key_1a);

        assertFalse(key_1a.equals(""));
        assertTrue(key_1a.isCompound());
        assertFalse(key_1a.isUnique());
    }

    @Test
    public void testKeyInvalidField() {
        try {
            KeyBuilder.init(CompoundKeyFooDesc.INSTANCE).set(FooDesc.ID, 1).set(
                    CompoundKeyFooDesc.NAME, "a").getKey();
            fail("Expected Exception");
        } catch (Exception e) {
            assertEquals("Field Foo.id is not defined for type 'CompoundKeyFooDesc'", e.getMessage());
        }
    }

    private void checkComparison(Key k1, Key k2, int result) {
        assertEquals(result, k1.compareTo(k2));
    }
}
