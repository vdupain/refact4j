package org.refact4j.functor.aggregate;

import java.util.Collections;

class ComparableAggregateVisitor<T extends Object & Comparable<? super T>> extends AbstractAggregateVisitor<T> {

    public void visitMinValue(MinValue<T> minValue) {
        setValue(Collections.min(minValue.getArg()));
    }

    public void visitMaxValue(MaxValue<T> maxValue) {
        setValue(Collections.max(maxValue.getArg()));
    }

}
