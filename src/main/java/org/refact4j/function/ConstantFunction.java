package org.refact4j.function;

/**
 * ConstantFunction function is a function that returns the given constant
 * value given at construction.
 *
 * @param <R>
 */
public class ConstantFunction<R> implements java.util.function.Function<Object, R> {

    private final R constant;

    public ConstantFunction(final R constant) {
        this.constant = constant;
    }

    /**
     * Returns the constant value given at construction.
     *
     * @return The constant value.
     */
    public R apply(Object arg) {
        return this.constant;
    }

    public R getConstant() {
        return constant;
    }
}
