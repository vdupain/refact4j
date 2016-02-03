package org.refact4j.function;

import org.refact4j.visitor.Visitor;

import java.util.function.BiFunction;

/**
 * Bind2nd is a unary function that wraps a given BiFunction, passing a
 * constant value as the second argument of the child function. The runtime
 * argument is passed as the first argument of the child function.
 *
 * @param <C>
 * @param <T>
 * @param <R>
 */
public class Bind2nd<C, T, R> extends AbstractFunction<T, R> {

    private final BiFunction<T, C, R> biFunction;
    private final C constant;

    public Bind2nd(BiFunction<T, C, R> functor, C constant) {
        this.constant = constant;
        biFunction = functor;
    }

    /**
     * Given one argument, passes the constant value and the argument to the
     * child function and returns the result.
     *
     * @return The result of the evaluation.
     */
    @Override
    protected R evaluate(T arg) {
        return biFunction.apply(arg, constant);
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
        if (visitor instanceof Bind2ndVisitor) {
            ((Bind2ndVisitor) visitor).visitBind2nd(this);
        }
    }

    public interface Bind2ndVisitor extends Visitor {
        void visitBind2nd(Bind2nd bind2nd);
    }

}
