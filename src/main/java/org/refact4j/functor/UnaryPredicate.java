package org.refact4j.functor;

/**
 * UnaryPredicate is an interface that identify a functor object that takes one
 * argument and returns a boolean. The argument is of type T. This unary
 * predicate is a kind of "y=f(x)" function where y=true/false.
 *
 * @param <T>
 */
public interface UnaryPredicate<T> extends java.util.function.Function<T, Boolean> {
}
