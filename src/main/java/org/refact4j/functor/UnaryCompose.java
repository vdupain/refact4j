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
public class UnaryCompose<F1, T, R> implements UnaryFunctor<T, R>, Visitable {

    private final UnaryFunctor<F1, R> firstUnaryFunctor;

    private final UnaryFunctor<T, F1> secondUnaryFunctor;

    public UnaryCompose(UnaryFunctor<F1, R> unaryFunctor1, UnaryFunctor<T, F1> unaryFunctor2) {
        this.firstUnaryFunctor = unaryFunctor1;
        this.secondUnaryFunctor = unaryFunctor2;
    }

    /**
     * f(g(x))
     */
    public R eval(T arg) {
        return firstUnaryFunctor.eval(secondUnaryFunctor.eval(arg));
    }

    public UnaryFunctor<F1, R> getFirstUnaryFunctor() {
        return firstUnaryFunctor;
    }

    public UnaryFunctor<T, F1> getSecondUnaryFunctor() {
        return secondUnaryFunctor;
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
