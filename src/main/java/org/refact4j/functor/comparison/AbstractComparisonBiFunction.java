package org.refact4j.functor.comparison;

import org.refact4j.functor.AbstractBiFunction;

import java.util.Comparator;

abstract class AbstractComparisonBiFunction<T> extends AbstractBiFunction<T, T, T> {
    private AbstractComparisonVisitor<? super T> visitor = new ComparableComparisonVisitor();

    void setComparator(Comparator<T> comparator) {
        if (comparator != null) {
            this.visitor = new ComparatorComparisonVisitor<>(comparator);
        }
    }

    protected T evaluate(T arg1, T arg2) {
        this.accept(this.visitor);
        return (T) visitor.getValue();
    }

}
