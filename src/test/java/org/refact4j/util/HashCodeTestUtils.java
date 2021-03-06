package org.refact4j.util;

import junit.framework.Assert;

public class HashCodeTestUtils {

    public static void checkEqualsAndHashCode(Object equals1,
                                              Object equals2, Object different) {
        if (equals1 == equals2) {
            throw new IllegalArgumentException(
                    "The first two parameters must not refer to the same object");
        }
        Assert.assertEquals(equals1, equals2);
        Assert.assertEquals(equals2, equals1);
        Assert.assertFalse(equals1.equals(different));
        Assert.assertEquals(equals1.hashCode(), equals2.hashCode());
    }

}
