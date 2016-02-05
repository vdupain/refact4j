package org.refact4j.function;

import org.junit.Test;
import org.refact4j.function.comparison.*;
import org.refact4j.util.ComparatorHelper;

import java.util.Comparator;

import static org.junit.Assert.*;


public class ComparisonFunctionsTest {
    private final Double d1 = 1.2345;

    private final Double d2 = 6.789;

    private final String s1 = "azerty";

    private final String s2 = "qwerty";

    private final Comparator comparator = (o1, o2) -> ComparatorHelper.compare(((Comparable) o1), ((Comparable) o2));

    @Test
    public void testEqual() {
        Equal equal = new Equal();
        assertEqual(equal);

        equal = new Equal(comparator);
        assertEqual(equal);
    }

    @Test
    public void testLessAndLessEqual() {
        Less less = new Less();
        LessEqual lessEqual = new LessEqual();
        assertLessAndLessEqual(less, lessEqual);

        less = new Less(comparator);
        lessEqual = new LessEqual(comparator);
        assertLessAndLessEqual(less, lessEqual);
    }

    @Test
    public void testGreaterAndGreaterEqual() {
        Greater greater = new Greater();
        GreaterEqual greaterEqual = new GreaterEqual();
        assertGreaterAndGreaterEqual(greater, greaterEqual);

        greater = new Greater(comparator);
        greaterEqual = new GreaterEqual(comparator);
        assertGreaterAndGreaterEqual(greater, greaterEqual);
    }

    @Test
    public void testMinAndMax() {
        Min min = new Min();
        Max max = new Max();
        assertMinAndMax(min, max);

        min = new Min(comparator);
        max = new Max(comparator);
        assertMinAndMax(min, max);
    }

    private void assertEqual(Equal equal) {
        assertTrue(equal.apply(d1, d1));
        assertFalse(equal.apply(d1, d2));
        assertTrue(equal.apply(s1, s1));
        assertFalse(equal.apply(s1, s2));
    }

    private void assertLessAndLessEqual(Less less, LessEqual lessEqual) {
        assertFalse(less.apply(d1, d1));
        assertTrue(lessEqual.apply(d1, d1));
        assertTrue(less.apply(d1, d2));
        assertTrue(lessEqual.apply(d1, d2));
        assertFalse(less.apply(d2, d1));
        assertTrue(less.apply(s1, s2));
        assertFalse(less.apply(s2, s1));
        assertFalse(lessEqual.apply(d1, null));
        assertTrue(lessEqual.apply(null, d1));
        assertTrue(lessEqual.apply(null, null));
    }

    private void assertGreaterAndGreaterEqual(Greater greater,
                                              GreaterEqual greaterEqual) {
        assertFalse(greater.apply(d1, d1));
        assertTrue(greaterEqual.apply(d1, d1));
        assertFalse(greater.apply(d1, d2));
        assertFalse(greaterEqual.apply(d1, d2));
        assertTrue(greater.apply(d2, d1));
        assertFalse(greater.apply(s1, s2));
        assertTrue(greater.apply(s2, s1));
        assertTrue(greaterEqual.apply(d1, null));
        assertFalse(greaterEqual.apply(null, d1));
        assertTrue(greaterEqual.apply(null, null));
    }

    private void assertMinAndMax(Min min, Max max) {
        assertEquals(d1, min.apply(d1, d1));
        assertEquals(d1, min.apply(d1, d2));
        assertEquals(d1, min.apply(d2, d1));
        assertEquals(d1, max.apply(d1, d1));
        assertEquals(d2, max.apply(d1, d2));
        assertEquals(d2, max.apply(d2, d1));
    }

}
