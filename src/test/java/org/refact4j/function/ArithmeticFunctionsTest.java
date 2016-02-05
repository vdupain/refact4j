package org.refact4j.function;

import org.junit.Test;
import org.refact4j.function.arithmetic.*;

import static org.junit.Assert.assertEquals;

public class ArithmeticFunctionsTest {
    private final double d1 = 12345.;
    private final double d2 = 6789.;
    private final int i1 = 12345;
    private final int i2 = 6789;

    @Test
    public void testMultiplies() {
        assertEquals((Double) (d1 * d2), new Multiplies<Double>().apply(d1, d2));
        assertEquals((Integer) (i1 * i2),
                new Multiplies<Integer>().apply(i1, i2));
    }

    @Test
    public void testDivides() {
        assertEquals((Double) (d1 / d2), new Divides<Double>().apply(d1, d2));
        assertEquals((Integer) (i1 / i2), new Divides<Integer>().apply(i1, i2));
    }

    @Test
    public void testMinus() {
        assertEquals((Double) (d1 - d2), new Minus<Double>().apply(d1, d2));
        assertEquals((Integer) (i1 - i2), new Minus<Integer>().apply(i1, i2));
    }

    @Test
    public void testPlus() {
        assertEquals((Double) (d1 + d2), new Plus<Double>().apply(d1, d2));
        assertEquals((Integer) (i1 + i2), new Plus<Integer>().apply(i1, i2));
    }

    @Test
    public void testNegate() {
        assertEquals((Double) (-d1), new Negate<Double>().apply(d1));
        assertEquals((Integer) (-i1), new Negate<Integer>().apply(i1));
    }

}
