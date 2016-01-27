package org.refact4j.functor.comparison;

import org.refact4j.functor.AbstractUnaryPredicate;
import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * NotNull is an Unary Predicate that returns true when its argument is not
 * null.
 *
 * @param <T>
 */
public class NotNull<T> extends AbstractUnaryPredicate<T> {

    private final NotEqual<T> notEqual = new NotEqual<T>();

    public NotNull() {
        this(null);
    }

    private NotNull(Comparator<T> comparator) {
        notEqual.setComparator(comparator);
    }

    @Override
    public boolean test(T arg) {
        return notEqual.apply(arg, null);
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
