package org.refact4j.functor.commons;

import org.refact4j.functor.AbstractUnaryPredicate;
import org.refact4j.functor.logical.Not;
import org.refact4j.visitor.Visitor;

/**
 * NotIn is an Unary Predicate that returns true when its argument is not
 * contains on the given values.
 *
 * @param <T>
 */

public class NotIn<T> extends AbstractUnaryPredicate<T> {

    private final Object[] values;

    public NotIn(final Object[] values) {
        this.values = values;
    }

    public Object[] getValues() {
        return this.values;
    }

    @Override
    public boolean test(T arg) {
        return new Not().compose(new In<>(values)).apply(arg);
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof NotInVisitor) {
            ((NotInVisitor) visitor).visitNotIn(this);
        }
    }

    public interface NotInVisitor extends Visitor {
        void visitNotIn(NotIn<?> notIn);
    }

}
