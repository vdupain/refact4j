package org.refact4j.function;

import org.refact4j.visitor.Visitor;

import java.util.function.BiFunction;

/**
 * Bind1st is a unary function that wraps a given BiFunction, passing a
 * constant value as the first argument of the child function. The runtime
 * argument is passed as the second argument of the child function.
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
     * child function and returns the result.
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
