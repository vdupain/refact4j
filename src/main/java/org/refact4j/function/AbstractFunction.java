package org.refact4j.function;

import org.refact4j.visitor.Visitable;

/**
 * This abstract class provides a skeletal implementation of the Function
 * interface, to minimize the effort required to implement this interface. To
 * implement a unary function, the programmer needs only to extend this class and
 * provide implementation for the apply method.
 *
 * @param <T>
 */
public abstract class AbstractFunction<T, R> implements Visitable, java.util.function.Function<T, R> {
    private T arg;

    protected abstract R evaluate(T arg);

    public R apply(T arg) {
        this.arg = arg;
        return evaluate(arg);
    }

    public T getArg() {
        return arg;
    }

}
