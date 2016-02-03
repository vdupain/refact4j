package org.refact4j.function.comparison;

import org.refact4j.visitor.Visitor;

/**
 * Null is an Unary Predicate that returns true when its argument is null.
 *
 * @param <T>
 */
public class Null<T> implements org.refact4j.function.UnaryPredicate<T> {

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