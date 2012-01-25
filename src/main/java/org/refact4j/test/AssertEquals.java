package org.refact4j.test;

import org.junit.Assert;

public class AssertEquals<T> extends AbstractAssertComparisonHandler<T> {

    @Override
    public void assertion(T expected, T actual) {
        Assert.assertEquals(message, expected, actual);
    }

}
