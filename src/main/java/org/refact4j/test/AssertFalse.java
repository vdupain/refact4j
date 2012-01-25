package org.refact4j.test;

import org.junit.Assert;

public class AssertFalse extends AbstractAssertConditionHandler {

    @Override
    public void assertion(Boolean condition) {
        Assert.assertFalse(getMessage(), condition);
    }

}
