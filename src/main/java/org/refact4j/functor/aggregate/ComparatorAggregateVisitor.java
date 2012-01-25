package org.refact4j.functor.aggregate;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

class ComparatorAggregateVisitor<T> extends AbstractAggregateVisitor<T> {

    private final Comparator<? super T> comparator;

    public ComparatorAggregateVisitor(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public void visitMinValue(MinValue<T> minValue) {
        setValue(Collections.min(minValue.getArg(), comparator));
    }

    public void visitMaxValue(MaxValue<T> maxValue) {
        Collection<? extends T> arg = maxValue.getArg();
        setValue(Collections.max(arg, comparator));
    }

}
