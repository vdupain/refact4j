package org.refact4j.functor;

import org.refact4j.visitor.Visitable;

/**
 * This abstract class provides a skeletal implementation of the BinaryPredicate
 * interface, to minimize the effort required to implement this interface. To
 * implement a binary predicate, the programmer needs only to extend this class
 * and provide implementation for the evaluate method.
 *
 * @param <T1>
 * @param <T2>
 */
public abstract class AbstractBinaryPredicate<T1, T2> implements BinaryPredicate<T1, T2>, Visitable {
    private T1 firstArg;

    private T2 secondArg;

    private Boolean result;

    protected abstract Boolean evaluate(T1 firstArg, T2 secondArg);

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

    public Boolean getResult() {
        return result;
    }

}
