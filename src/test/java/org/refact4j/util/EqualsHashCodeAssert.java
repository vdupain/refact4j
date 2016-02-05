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

}
