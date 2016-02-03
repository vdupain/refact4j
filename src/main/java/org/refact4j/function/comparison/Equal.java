package org.refact4j.function.comparison;

import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * Equal is a comparison operator. This Binary Predicate that returns true for
 * object arguments arg1 and arg2 when arg1 == arg2 using the built-in equals()
 * method or an optional Comparator given at construction
 */
public class Equal<T> extends AbstractComparisonBiPredicate<T> {

    public Equal() {
        setComparator(null);
    }

    public Equal(Comparator<T> comparator) {
        setComparator(comparator);
    }

    @SuppressWarnings("unchecked")
    public void accept(Visitor visitor) {
        if (visitor instanceof EqualVisitor) {
            ((EqualVisitor) visitor).visitEqual(this);
        }
    }

    public interface EqualVisitor<T> extends Visitor {
        void visitEqual(Equal<T> equal);
    }

}
