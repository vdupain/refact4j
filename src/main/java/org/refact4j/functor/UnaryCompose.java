package org.refact4j.functor;

import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

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
public class UnaryCompose<F1, T, R> implements Visitable, java.util.function.Function<T, R> {

    private final java.util.function.Function<F1, R> firstFunction;

    private final java.util.function.Function<T, F1> secondFunction;

    public UnaryCompose(java.util.function.Function<F1, R> function1, java.util.function.Function<T, F1> function2) {
        this.firstFunction = function1;
        this.secondFunction = function2;
    }

    /**
     * f(g(x))
     */
    public R apply(T arg) {
        return firstFunction.apply(secondFunction.apply(arg));
    }

    public java.util.function.Function<F1, R> getFirstFunction() {
        return firstFunction;
    }

    public java.util.function.Function<T, F1> getSecondFunction() {
        return secondFunction;
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
