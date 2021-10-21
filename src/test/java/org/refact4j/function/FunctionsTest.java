package org.refact4j.function;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;
import static org.refact4j.function.Functions.*;

public class FunctionsTest {

    @Test
    public void testConstantArgUnaryFunction() {
        double discountRate = 0.2;
        double taxRate = 0.20;

        Function<Double, Double> discountedPrice = bind1st((o1, o2) -> o1*o2,
                1 - discountRate);
        Function<Double, Double> taxedPrice = bind1st((o1, o2) -> o1*o2, 1 + taxRate);

        double value = 100.;
        value = discountedPrice.apply(value);
        assertEquals(value, 80., 0);
        value = taxedPrice.apply(value);
        assertEquals(value, 96., 0);

        value = 100.;
        value = discountedPrice.apply(taxedPrice.apply(value));
        assertEquals(value, 96., 0);

        value = 100.;
        value = discountedPrice.compose(taxedPrice).apply(value);
        assertEquals(value, 96., 0);

        Function<Double, Double> functor = bind2nd((o1, o2) -> o1*o2, 1 + taxRate);
        value = 100.;
        value = discountedPrice.apply(value);
        assertEquals(value, 80., 0);
        value = functor.apply(value);
        assertEquals(value, 96., 0);
    }


}
