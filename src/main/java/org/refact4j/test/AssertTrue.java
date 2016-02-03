package org.refact4j.test;

import org.junit.Assert;

public class AssertTrue extends AbstractAssertConditionHandler {

    @Override
    public void assertion(Boolean condition) {
        Assert.assertTrue(getMessage(), condition);
    }
}
