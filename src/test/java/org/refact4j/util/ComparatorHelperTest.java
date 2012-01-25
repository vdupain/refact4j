package org.refact4j.util;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ComparatorHelperTest {

    @Test
    public void testCompare() {
        assertTrue(ComparatorHelper.compare(null, null) == 0);
        assertTrue(ComparatorHelper.compare("abc", "abc") == 0);
        assertTrue(ComparatorHelper.compare("abc", null) == 1);
        assertTrue(ComparatorHelper.compare("abc", "a") == 2);
        assertTrue(ComparatorHelper.compare(null, "abc") == -1);
        assertTrue(ComparatorHelper.compare("a", "abc") == -2);
    }
}
