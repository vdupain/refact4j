package org.refact4j.functor.comparison;

import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * Less is a comparison operator. This Binary Predicate that returns true for
 * object arguments arg1 and arg2 when arg1 < arg2. The comparison is performed
 * using a comparator supplied at construction time, although a default
 * comparator will be used if the nested Comparable class' default constructor
 * is used.
 */
public class Less<T> extends AbstractComparisonBiPredicate<T> {

    public Less() {
        setComparator(null);
    }

    public Less(Comparator<T> comparator) {
        setComparator(comparator);
    }

    @SuppressWarnings("unchecked")
    public void accept(Visitor visitor) {
        if (visitor instanceof LessVisitor) {
            ((LessVisitor) visitor).visitLess(this);
        }
    }

    public interface LessVisitor<T> extends Visitor {
        void visitLess(Less<T> less);
    }

}
