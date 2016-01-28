package org.refact4j.functor.comparison;

import org.refact4j.functor.AbstractUnaryPredicate;
import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * Null is an Unary Predicate that returns true when its argument is null.
 *
 * @param <T>
 */
public class Null<T> extends AbstractUnaryPredicate<T> {

    @Override
    public boolean test(T arg) {
        return arg == null;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof NullVisitor) {
            ((NullVisitor) visitor).visitNull(this);
        }
    }

    public interface NullVisitor extends Visitor {
        void visitNull(Null<?> nul);
    }

}