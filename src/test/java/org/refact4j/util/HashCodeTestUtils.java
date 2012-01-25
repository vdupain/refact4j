package org.refact4j.util;

import junit.framework.Assert;

public class HashCodeTestUtils {
    public static void checkEqualsAndHashCode(Object equals1, Object equals2,
                                              Object different) {
        if (equals1 == equals2) {
            throw new IllegalArgumentException(
                    "The first two parameters must not refer to the same object");
        }
        Assert.assertEquals(equals1, equals2);
        Assert.assertEquals(equals2, equals1);
        Assert.assertFalse(equals1.equals(different));
        checkHashCode(equals1, equals2);
    }

    public static void checkEqualsAndHashCode(String message, Object equals1,
                                              Object equals2, Object different) {
        if (equals1 == equals2) {
            throw new IllegalArgumentException(
                    message
                            + "\nThe first two parameters must not refer to the same object");
        }
        Assert.assertEquals(message, equals1, equals2);
        Assert.assertEquals(message, equals2, equals1);
        Assert.assertFalse(message, equals1.equals(different));
        checkHashCode(message, equals1, equals2);
    }

    public static void checkHashCode(Object equals1, Object equals2) {
        Assert.assertEquals(equals1.hashCode(), equals2.hashCode());
    }

    public static void checkHashCode(String message, Object equals1,
                                     Object equals2) {
        Assert.assertEquals(message, equals1.hashCode(), equals2.hashCode());
    }
}
