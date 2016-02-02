package org.refact4j.functor.comparison;

import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * Greater is a comparison operator. This Binary Predicate that returns true for
 * object arguments arg1 and arg2 when arg1 > arg2. The comparison is performed
 * using a comparator supplied at construction time, although a default
 * comparator will be used if the nested Comparable class' default constructor
 * is used.
 */
public class Greater<T> extends AbstractComparisonBiPredicate<T> {

    public Greater() {
        setComparator(null);
    }

    public Greater(Comparator<T> comparator) {
        setComparator(comparator);
    }

    @SuppressWarnings("unchecked")
    public void accept(Visitor visitor) {
        if (visitor instanceof GreaterVisitor) {
            ((GreaterVisitor) visitor).visitGreater(this);
        }
    }

    public interface GreaterVisitor<T> extends Visitor {
        void visitGreater(Greater<T> greater);
    }

}
