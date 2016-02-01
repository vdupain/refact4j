package org.refact4j.functor;

import org.junit.Before;
import org.junit.Test;
import org.refact4j.functor.aggregate.MaxValue;
import org.refact4j.functor.aggregate.MinValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;


public class AggregateFunctionsTest {
    Integer expectedMinValue = 1;
    Integer expectedMaxValue = 10;

    Collection<Integer> values;

    Comparator<Integer> comparator = Integer::compareTo;

    @Before
    public void setUp() {
        values = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            values.add(i + 1);
        }
    }

    @Test
    public void testMinAndMax() {
        MaxValue<Integer> maxValue = new MaxValue<>(comparator);
        MinValue<Integer> minValue = new MinValue<>(comparator);
        assertMinAndMax(minValue, maxValue);

        maxValue = new MaxValue<>();
        minValue = new MinValue<>();
        assertMinAndMax(minValue, maxValue);
    }

    private void assertMinAndMax(MinValue<Integer> min, MaxValue<Integer> max) {
        assertEquals(expectedMinValue, min.apply(values));
        assertEquals(expectedMaxValue, max.apply(values));
    }

}
