package org.refact4j.function.comparison;

import org.refact4j.function.AbstractBiPredicate;

import java.util.Comparator;

abstract class AbstractComparisonBiPredicate<T> extends AbstractBiPredicate<T, T> {
    private AbstractComparisonVisitor<? super T> visitor = new ComparableComparisonVisitor();

    public void setComparator(Comparator<? super T> comparator) {
        if (comparator != null) {
            this.visitor = new ComparatorComparisonVisitor<>(comparator);
        }
    }

    public boolean test(T arg1, T arg2) {
        this.accept(this.visitor);
        return visitor.getResult();
    }

}
