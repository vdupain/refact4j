package org.refact4j.functor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.refact4j.functor.comparison.Equal;
import org.refact4j.functor.comparison.Greater;
import org.refact4j.functor.comparison.GreaterEqual;
import org.refact4j.functor.comparison.Less;
import org.refact4j.functor.comparison.LessEqual;
import org.refact4j.functor.comparison.Max;
import org.refact4j.functor.comparison.Min;
import org.refact4j.functor.comparison.NotEqual;
import org.refact4j.util.ComparatorHelper;

import java.util.Comparator;


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
        assertTrue(equal.eval(d1, d1));
        assertFalse(equal.eval(d1, d2));
        assertTrue(equal.eval(s1, s1));
        assertFalse(equal.eval(s1, s2));
        assertFalse(notEqual.eval(d1, d1));
        assertTrue(notEqual.eval(d1, d2));
        assertFalse(notEqual.eval(s1, s1));
        assertTrue(notEqual.eval(s1, s2));
    }

    private void assertLessAndLessEqual(Less less, LessEqual lessEqual) {
        assertFalse(less.eval(d1, d1));
        assertTrue(lessEqual.eval(d1, d1));
        assertTrue(less.eval(d1, d2));
        assertTrue(lessEqual.eval(d1, d2));
        assertFalse(less.eval(d2, d1));
        assertTrue(less.eval(s1, s2));
        assertFalse(less.eval(s2, s1));
        assertFalse(lessEqual.eval(d1, null));
        assertTrue(lessEqual.eval(null, d1));
        assertTrue(lessEqual.eval(null, null));
    }

    private void assertGreaterAndGreaterEqual(Greater greater,
                                              GreaterEqual greaterEqual) {
        assertFalse(greater.eval(d1, d1));
        assertTrue(greaterEqual.eval(d1, d1));
        assertFalse(greater.eval(d1, d2));
        assertFalse(greaterEqual.eval(d1, d2));
        assertTrue(greater.eval(d2, d1));
        assertFalse(greater.eval(s1, s2));
        assertTrue(greater.eval(s2, s1));
        assertTrue(greaterEqual.eval(d1, null));
        assertFalse(greaterEqual.eval(null, d1));
        assertTrue(greaterEqual.eval(null, null));
    }

    private void assertMinAndMax(Min min, Max max) {
        assertEquals(d1, min.eval(d1, d1));
        assertEquals(d1, min.eval(d1, d2));
        assertEquals(d1, min.eval(d2, d1));
        assertEquals(d1, max.eval(d1, d1));
        assertEquals(d2, max.eval(d1, d2));
        assertEquals(d2, max.eval(d2, d1));
    }

}
