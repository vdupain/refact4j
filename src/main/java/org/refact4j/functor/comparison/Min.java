package org.refact4j.functor.comparison;

import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * Min is comparison operator. This Binary Functor that returns the lesser of
 * two object arguments arg1 and arg2. The comparison is performed using a
 * comparator supplied at construction time, although a default comparator will
 * be used if the nested Comparable class' default constructor is used.
 */
public class Min<T> extends AbstractComparisonBiFunction<T> {

    public Min() {
        this(null);
    }

    public Min(Comparator<T> comparator) {
        setComparator(comparator);
    }

    @SuppressWarnings("unchecked")
    public void accept(Visitor visitor) {
        if (visitor instanceof MinVisitor) {
            ((MinVisitor) visitor).visitMin(this);
        }
    }

    public interface MinVisitor<T> extends Visitor {
        void visitMin(Min<T> min);
    }

}
