package org.refact4j.function;

import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

import java.util.function.Predicate;

/**
 * UnaryPredicate is an interface that identify a function object that takes one
 * argument and returns a boolean. The argument is of type T. This unary
 * predicate is a kind of "y=f(x)" function where y=true/false.
 *
 * @param <T>
 */
public interface UnaryPredicate<T> extends Predicate<T>, java.util.function.Function<T, Boolean>, Visitable {

    default Boolean apply(T arg) {
        return test(arg);
    }

    default void accept(Visitor visitor) {
    }

}
