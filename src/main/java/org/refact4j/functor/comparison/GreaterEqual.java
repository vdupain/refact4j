package org.refact4j.functor.comparison;

import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * GreaterEqual is a comparison operator. This Binary Predicate that returns
 * true for object arguments arg1 and arg2 when arg1 >= arg2. The comparison is
 * performed using a comparator supplied at construction time, although a
 * default comparator will be used if the nested Comparable class' default
 * constructor is used.
 */
public class GreaterEqual<T> extends AbstractComparisonBinaryPredicate<T> {

    public GreaterEqual() {
        setComparator(null);
    }

    public GreaterEqual(Comparator<T> comparator) {
        setComparator(comparator);
    }

    @SuppressWarnings("unchecked")
    public void accept(Visitor visitor) {
        if (visitor instanceof GreaterEqualVisitor) {
            ((GreaterEqualVisitor) visitor).visitGreaterEqual(this);
        }
    }

    public interface GreaterEqualVisitor<T> extends Visitor {
        void visitGreaterEqual(GreaterEqual<T> greaterEqual);
    }

}
