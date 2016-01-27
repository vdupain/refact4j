package org.refact4j.functor;

import org.refact4j.visitor.Visitable;

/**
 * This abstract class provides a skeletal implementation of the UnaryPredicate
 * interface, to minimize the effort required to implement this interface. To
 * implement a unary predicate, the programmer needs only to extend this class
 * and provide implementation for the evaluate method.
 *
 * @param <T>
 */
public abstract class AbstractUnaryPredicate<T> implements UnaryPredicate<T>, Visitable {

    private T arg;

    private boolean result;

    public abstract boolean evaluate(T arg);

    public Boolean apply(T arg) {
        this.arg = arg;
        result = evaluate(arg);
        return result;
    }

    public T getArg() {
        return arg;
    }

    public boolean getResult() {
        return result;
    }

}
