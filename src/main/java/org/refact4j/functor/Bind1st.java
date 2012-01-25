package org.refact4j.functor;

import org.refact4j.visitor.Visitor;

/**
 * Bind1st is a unary functor that wraps a given BinaryFunctor, passing a
 * constant value as the first argument of the child functor. The runtime
 * argument is passed as the second argument of the child functor.
 *
 * @param <C>
 * @param <T>
 * @param <R>
 */

public class Bind1st<C, T, R> extends AbstractUnaryFunctor<T, R> {

    private final BinaryFunctor<C, T, R> binaryFunctor;
    private final C constant;

    public Bind1st(BinaryFunctor<C, T, R> functor, C constant) {
        this.constant = constant;
        binaryFunctor = functor;
    }

    /**
     * Given one argument, passes the constant value and the argument to the
     * child functor and returns the result.
     *
     * @return The result of the evaluation.
     */
    public R evaluate(T arg) {
        return binaryFunctor.eval(constant, arg);
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
