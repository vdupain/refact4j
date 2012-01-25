package org.refact4j.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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
