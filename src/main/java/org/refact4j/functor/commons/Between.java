package org.refact4j.functor.commons;

import org.refact4j.functor.AbstractUnaryPredicate;
import org.refact4j.functor.comparison.GreaterEqual;
import org.refact4j.functor.comparison.LessEqual;
import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * Between is an Unary Predicate that returns true when its argument is between
 * two given values. The range (infValue and supValue) is inclusive.
 *
 * @param <T>
 */
public class Between<T> extends AbstractUnaryPredicate<T> {
    private final T infValue;
    private final T supValue;
    private final GreaterEqual<T> greaterEqual = new GreaterEqual<T>();
    private final LessEqual<T> lessEqual = new LessEqual<T>();

    public Between(final T infValue, final T supValue) {
        this(infValue, supValue, null);
    }

    private Between(final T infValue, final T supValue, Comparator<T> comparator) {
        this.infValue = infValue;
        this.supValue = supValue;
        this.greaterEqual.setComparator(comparator);
        this.lessEqual.setComparator(comparator);
    }

    public T getInfValue() {
        return infValue;
    }

    public T getSupValue() {
        return supValue;
    }

    @Override
    public Boolean evaluate(T arg) {
        return greaterEqual.apply(arg, infValue) && lessEqual.apply(arg, supValue);
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof BetweenVisitor) {
            ((BetweenVisitor) visitor).visitBetween(this);
        }
    }

    public interface BetweenVisitor extends Visitor {
        void visitBetween(Between<?> between);
    }

}
