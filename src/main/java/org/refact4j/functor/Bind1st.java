package org.refact4j.functor;

import org.refact4j.visitor.Visitor;

/**
 * Bind1st is a unary functor that wraps a given BiFunction, passing a
 * constant value as the first argument of the child functor. The runtime
 * argument is passed as the second argument of the child functor.
 *
 * @param <C>
 * @param <T>
 * @param <R>
 */

public class Bind1st<C, T, R> extends AbstractFunction<T, R> {

    private final BiFunction<C, T, R> biFunction;
    private final C constant;

    public Bind1st(BiFunction<C, T, R> functor, C constant) {
        this.constant = constant;
        biFunction = functor;
    }

    /**
     * Given one argument, passes the constant value and the argument to the
     * child functor and returns the result.
     *
     * @return The result of the evaluation.
     */
    public R evaluate(T arg) {
        return biFunction.apply(constant, arg);
    }

    /**
     * Returns the constant value.
     *
     * @return The constant value.
     */
    public C getConstant() {
        return constant;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof Bind1stVisitor) {
            ((Bind1stVisitor) visitor).visitBind1st(this);
        }
    }

    public interface Bind1stVisitor extends Visitor {
        void visitBind1st(Bind1st bind1st);
    }

}
