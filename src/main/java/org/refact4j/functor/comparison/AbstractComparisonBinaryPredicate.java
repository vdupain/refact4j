package org.refact4j.functor.comparison;

import org.refact4j.functor.AbstractBinaryPredicate;

import java.util.Comparator;

abstract class AbstractComparisonBinaryPredicate<T> extends AbstractBinaryPredicate<T, T> {
    private AbstractComparisonVisitor<? super T> visitor = new ComparableComparisonVisitor();

    public void setComparator(Comparator<? super T> comparator) {
        if (comparator != null) {
            this.visitor = new ComparatorComparisonVisitor<T>(comparator);
        }
    }

    protected boolean evaluate(T arg1, T arg2) {
        this.accept(this.visitor);
        return visitor.getResult();
    }

}
