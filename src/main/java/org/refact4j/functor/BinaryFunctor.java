package org.refact4j.functor;

/**
 * BinaryFunctor is an interface that identify a functor object that takes two
 * arguments and returns a result. The first argument is of type T1, The second
 * argument is of type T2 and the result is of type R. This binary functor is a
 * kind of "y=f(x1,x2)" function.
 *
 * @param <T1>
 * @param <T2>
 * @param <R>
 */
public interface BinaryFunctor<T1, T2, R> {

    /**
     * Evaluates this functor and returns the result.
     *
     * @param firstArg  The first parameter.
     * @param secondArg The second parameter.
     * @return The result of the evaluation.
     */
    R eval(T1 firstArg, T2 secondArg);

}
