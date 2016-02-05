package org.refact4j.util;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EqualsHelperTest {

    @Test
    public void testEquals() {
        assertTrue(Objects.equals(null, null));
        assertTrue(Objects.equals("xxx", "xxx"));
        assertFalse(Objects.equals(null, "xxx"));
        assertFalse(Objects.equals("xxx", null));
        assertFalse(Objects.equals("xxx", "x"));
    }

}
