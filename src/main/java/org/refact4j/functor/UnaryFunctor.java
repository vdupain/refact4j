package org.refact4j.functor;

/**
 * UnaryFunctor is an interface that identify a functor object that takes one
 * argument and returns a result. The argument is of type T and the result is of
 * type R. This unary functor is a kind of "y=f(x)" function.
 *
 * @param <T>
 * @param <R>
 */
public interface UnaryFunctor<T, R> {

    /**
     * Evaluates this functor and returns the result.
     *
     * @param arg The parameter.
     * @return The result of the evaluation
     */
    R eval(T arg);
}
