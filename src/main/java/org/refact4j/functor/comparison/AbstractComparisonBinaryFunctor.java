package org.refact4j.functor.comparison;

import org.refact4j.functor.AbstractBinaryFunctor;

import java.util.Comparator;

abstract class AbstractComparisonBinaryFunctor<T> extends AbstractBinaryFunctor<T, T, T> {
    private AbstractComparisonVisitor<? super T> visitor = new ComparableComparisonVisitor();

    void setComparator(Comparator<T> comparator) {
        if (comparator != null) {
            this.visitor = new ComparatorComparisonVisitor<T>(comparator);
        }
    }

    protected T evaluate(T arg1, T arg2) {
        this.accept(this.visitor);
        return (T) visitor.getValue();
    }

}
