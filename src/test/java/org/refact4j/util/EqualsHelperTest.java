package org.refact4j.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EqualsHelperTest {

    @Test
    public void testEquals() {
        assertTrue(EqualsHelper.equals(null, null));
        assertTrue(EqualsHelper.equals("xxx", "xxx"));
        assertFalse(EqualsHelper.equals(null, "xxx"));
        assertFalse(EqualsHelper.equals("xxx", null));
        assertFalse(EqualsHelper.equals("xxx", "x"));
    }

}
