package org.refact4j.functor;

import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

import java.util.function.Function;

/**
 * UnaryCompose is a Unary Functor that passes the results of a Unary Functor as
 * the argument to a Unary Functor. This allows for the construction of compound
 * functors from the primitives found in the logical package. This functor is a
 * "y=f(g(x))" function.
 *
 * @param <F1>
 * @param <T>
 * @param <R>
 */
public class UnaryCompose<F1, T, R> implements Visitable, Function<T, R> {

    private final Function<F1, R> function;

    private final Function<T, F1> before;

    public UnaryCompose(Function<F1, R> function, Function<T, F1> before) {
        this.function = function;
        this.before = before;
    }

    /**
     * f(g(x))
     */
    public R apply(T arg) {
        return function.compose(before).apply(arg);
    }

    public java.util.function.Function<F1, R> getFunction() {
        return function;
    }

    public java.util.function.Function<T, F1> getBefore() {
        return before;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof UnaryComposeVisitor) {
            ((UnaryComposeVisitor) visitor).visitUnaryCompose(this);
        }
    }

    public interface UnaryComposeVisitor extends Visitor {
        void visitUnaryCompose(UnaryCompose<?, ?, ?> unaryCompose);
    }

}
