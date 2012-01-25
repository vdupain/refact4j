package org.refact4j.functor.comparison;

import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * Max is comparison operator. This Binary Functor that returns the greater of
 * two object arguments arg1 and arg2. The comparison is performed using a
 * comparator supplied at construction time, although a default comparator will
 * be used if the nested Comparable class' default constructor is used.
 */
public class Max<T> extends AbstractComparisonBinaryFunctor<T> {

    public Max() {
        this(null);
    }

    public Max(Comparator<T> comparator) {
        setComparator(comparator);
    }

    @SuppressWarnings("unchecked")
    public void accept(Visitor visitor) {
        if (visitor instanceof MaxVisitor) {
            ((MaxVisitor) visitor).visitMax(this);
        }
    }

    public interface MaxVisitor<T> extends Visitor {
        void visitMax(Max<T> max);
    }

}
