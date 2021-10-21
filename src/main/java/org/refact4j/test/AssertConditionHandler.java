package org.refact4j.test;

import java.util.function.Predicate;

public interface AssertConditionHandler extends Predicate<Boolean>, AssertionHandler {

    void setCondition(Boolean condition);

}
