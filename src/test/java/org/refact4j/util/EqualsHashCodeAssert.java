package org.refact4j.util;

import org.junit.Assert;

public class EqualsHashCodeAssert extends Assert {

    public static void assertEqualsHashCodeCoherent(Object obj1,
                                                    Object obj2) {
        assertEquals(obj1, obj2);
        assertEquals(obj1.hashCode(), obj2.hashCode());
    }

    public static void assertEqualsIsReflexive(Object obj) {
        assertEquals(obj, obj);
    }

    public static void assertEqualsIsSymmetric(Object obj1, Object obj2) {
        assertEquals(obj1, obj2);
        assertEquals(obj2, obj1);
    }

    public static void assertEqualsIsTransitive(Object obj1, Object obj2,
                                                Object obj3) {
        assertEquals(obj1, obj2);
        assertEquals(obj2, obj3);
        assertEquals(obj1, obj3);
    }

    public static void assertEqualsNullComparisonReturnsFalse(Object obj) {
        assertFalse(obj.equals(null));
    }
}
