package org.refact4j.functor;

import org.refact4j.visitor.Visitable;

/**
 * This abstract class provides a skeletal implementation of the BinaryFunctor
 * interface, to minimize the effort required to implement this interface. To
 * implement a binary functor, the programmer needs only to extend this class
 * and provide implementation for the evaluate method.
 *
 * @param <T1>
 * @param <T2>
 * @param <R>
 */
public abstract class AbstractBinaryFunctor<T1, T2, R> implements BinaryFunctor<T1, T2, R>, Visitable {
    private T1 firstArg;

    private T2 secondArg;

    private R result;

    protected abstract R evaluate(T1 firstArg, T2 secondArg);

    public R eval(T1 firstArg, T2 secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
        result = evaluate(firstArg, secondArg);
        return result;
    }

    public T1 getFirstArg() {
        return firstArg;
    }

    public T2 getSecondArg() {
        return secondArg;
    }

    public R getResult() {
        return result;
    }

}
