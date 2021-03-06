package org.refact4j.function;

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
public abstract class AbstractBiPredicate<T1, T2> implements BinaryPredicate<T1, T2>, Visitable {
    private T1 firstArg;
    private T2 secondArg;

    public Boolean apply(T1 firstArg, T2 secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
        return test(firstArg, secondArg);
    }

    public T1 getFirstArg() {
        return firstArg;
    }

    public T2 getSecondArg() {
        return secondArg;
    }

}
