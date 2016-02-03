package org.refact4j.test;

import org.refact4j.function.UnaryPredicate;

public interface AssertConditionHandler extends UnaryPredicate<Boolean>, AssertionHandler {

    Boolean getCondition();

    void setCondition(Boolean condition);

}
