package org.refact4j.functor;

import org.refact4j.visitor.Visitable;

import java.util.function.Predicate;

/**
 * This abstract class provides a skeletal implementation of the UnaryPredicate
 * interface, to minimize the effort required to implement this interface. To
 * implement a unary predicate, the programmer needs only to extend this class
 * and provide implementation for the test method.
 *
 * @param <T>
 */
public abstract class AbstractUnaryPredicate<T> implements Predicate<T>, UnaryPredicate<T>, Visitable {

    public Boolean apply(T arg) {
        return test(arg);
    }

}
