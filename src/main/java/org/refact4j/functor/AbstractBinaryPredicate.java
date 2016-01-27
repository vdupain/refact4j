package org.refact4j.functor;

import org.refact4j.visitor.Visitable;

/**
 * This abstract class provides a skeletal implementation of the BinaryPredicate
 * interface, to minimize the effort required to implement this interface. To
 * implement a binary predicate, the programmer needs only to extend this class
 * and provide implementation for the test method.
 *
 * @param <T1>
 * @param <T2>
 */
public abstract class AbstractBinaryPredicate<T1, T2> implements BinaryPredicate<T1, T2>, Visitable {
    private T1 firstArg;

    private T2 secondArg;

    private boolean result;

    protected abstract boolean evaluate(T1 firstArg, T2 secondArg);

    public Boolean apply(T1 firstArg, T2 secondArg) {
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

    public boolean getResult() {
        return result;
    }

}
