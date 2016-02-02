package org.refact4j.functor.comparison;

import org.refact4j.visitor.Visitor;

/**
 * NotNull is an Unary Predicate that returns true when its argument is not
 * null.
 *
 * @param <T>
 */
public class NotNull<T> implements org.refact4j.functor.UnaryPredicate<T> {

    @Override
    public boolean test(T arg) {
        return arg != null;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof NotNullVisitor) {
            ((NotNullVisitor) visitor).visitNotNull(this);
        }
    }

    public interface NotNullVisitor extends Visitor {
        void visitNotNull(NotNull<?> notNull);
    }

}
