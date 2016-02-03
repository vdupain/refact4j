package org.refact4j.function;

import org.refact4j.visitor.Visitable;

/**
 * This abstract class provides a skeletal implementation of the BiFunction
 * interface, to minimize the effort required to implement this interface. To
 * implement a binary function, the programmer needs only to extend this class
 * and provide implementation for the test method.
 *
 * @param <T1>
 * @param <T2>
 * @param <R>
 */
public abstract class AbstractBiFunction<T1, T2, R> implements java.util.function.BiFunction<T1, T2, R>, Visitable {
    private T1 firstArg;

    private T2 secondArg;

    protected abstract R evaluate(T1 firstArg, T2 secondArg);

    public R apply(T1 firstArg, T2 secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
        return evaluate(firstArg, secondArg);
    }

    public T1 getFirstArg() {
        return firstArg;
    }

    public T2 getSecondArg() {
        return secondArg;
    }

}
