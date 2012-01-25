package org.refact4j.functor;

/**
 * ConstantUnaryFunctor functor is a functor that returns the given constant
 * value given at construction.
 *
 * @param <R>
 */
public class ConstantUnaryFunctor<R> implements UnaryFunctor<Object, R> {

    private final R constant;

    public ConstantUnaryFunctor(final R object) {
        this.constant = object;
    }

    /**
     * Returns the constant value given at construction.
     *
     * @return The constant value.
     */
    public R eval(Object arg) {
        return this.constant;
    }

    public R getConstant() {
        return constant;
    }
}
