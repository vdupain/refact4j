package org.refact4j.functor;

import org.junit.Test;
import org.refact4j.functor.comparison.*;
import org.refact4j.util.ComparatorHelper;

import java.util.Comparator;

import static org.junit.Assert.*;


public class ComparisonFunctorsTest {
    Double d1 = 1.2345;

    Double d2 = 6.789;

    String s1 = "azerty";

    String s2 = "qwerty";

    Comparator comparator = new Comparator() {
        public int compare(Object o1, Object o2) {
            return ComparatorHelper.compare(((Comparable) o1), ((Comparable) o2));
        }

    };

    @Test
    public void testEqualAndNotEqual() {
        Equal equal = new Equal();
        NotEqual notEqual = new NotEqual();

        assertEqualAndNotEqual(equal, notEqual);

        equal = new Equal(comparator);
        notEqual = new NotEqual(comparator);
        assertEqualAndNotEqual(equal, notEqual);

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

    private void assertEqualAndNotEqual(Equal equal, NotEqual notEqual) {
        assertTrue(equal.apply(d1, d1));
        assertFalse(equal.apply(d1, d2));
        assertTrue(equal.apply(s1, s1));
        assertFalse(equal.apply(s1, s2));
        assertFalse(notEqual.apply(d1, d1));
        assertTrue(notEqual.apply(d1, d2));
        assertFalse(notEqual.apply(s1, s1));
        assertTrue(notEqual.apply(s1, s2));
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
