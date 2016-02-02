package org.refact4j.test;

import org.junit.Assert;
import org.refact4j.visitor.Visitor;

public class AssertTrue extends AbstractAssertConditionHandler {

    @Override
    public void assertion(Boolean condition) {
        Assert.assertTrue(getMessage(), condition);
    }
}
