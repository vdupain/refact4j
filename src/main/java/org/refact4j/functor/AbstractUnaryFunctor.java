package org.refact4j.functor;

import org.refact4j.visitor.Visitable;

/**
 * This abstract class provides a skeletal implementation of the UnaryFunctor
 * interface, to minimize the effort required to implement this interface. To
 * implement a unary functor, the programmer needs only to extend this class and
 * provide implementation for the evaluate method.
 *
 * @param <T>
 */
public abstract class AbstractUnaryFunctor<T, R> implements UnaryFunctor<T, R>, Visitable {
    private T arg;

    private R result;

    protected abstract R evaluate(T arg);

    public R eval(T arg) {
        this.arg = arg;
        result = evaluate(arg);
        return result;
    }

    public T getArg() {
        return arg;
    }

    public R getResult() {
        return result;
    }

}
