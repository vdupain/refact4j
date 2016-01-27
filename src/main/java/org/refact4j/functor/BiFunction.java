package org.refact4j.functor;


/**
 * BiFunction is an interface that identify a functor object that takes two
 * arguments and returns a result. The first argument is of type T1, The second
 * argument is of type T2 and the result is of type R. This binary functor is a
 * kind of "y=f(x1,x2)" function.
 *
 * @param <T1>
 * @param <T2>
 * @param <R>
 */
public interface BiFunction<T1, T2, R> extends java.util.function.BiFunction<T1,T2,R> {


}
