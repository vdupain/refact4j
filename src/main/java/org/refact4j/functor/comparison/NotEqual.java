package org.refact4j.functor.comparison;

import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * Equal is a comparison operator. This Binary Predicate that returns true for
 * object arguments arg1 and arg2 when arg1 != arg2 using the built-in equals()
 * method or an optional Comparator given at construction.
 */
public class NotEqual<T> extends AbstractComparisonBinaryPredicate<T> {

    public NotEqual() {
        setComparator(null);
    }

    public NotEqual(Comparator<T> comparator) {
        setComparator(comparator);
    }

    @SuppressWarnings("unchecked")
    public void accept(Visitor visitor) {
        if (visitor instanceof NotEqualVisitor) {
            ((NotEqualVisitor) visitor).visitNotEqual(this);
        }
    }

    public interface NotEqualVisitor<T> extends Visitor {
        void visitNotEqual(NotEqual<T> notEqual);
    }

}
