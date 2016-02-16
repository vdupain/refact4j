package org.refact4j.function;

import org.junit.Before;
import org.junit.Test;
import org.refact4j.function.aggregate.MaxValue;
import org.refact4j.function.aggregate.MinValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;


public class AggregateFunctionsTest {
    private final Integer expectedMinValue = 1;
    private final Integer expectedMaxValue = 10;
    private final Comparator<Integer> comparator = Integer::compareTo;
    private Collection<Integer> values;

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
