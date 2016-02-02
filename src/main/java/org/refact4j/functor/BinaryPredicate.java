package org.refact4j.functor;

import java.util.function.BiPredicate;

/**
 * BinaryPredicate is an interface that identify a functor object that takes two
 * arguments and returns a boolean. The first argument is of type T1, The second
 * argument is of type T2. This binary predicate is a kind of "y=f(x1,x2)"
 * function where y=true/false.
 *
 * @param <T1>
 * @param <T2>
 */
public interface BinaryPredicate<T1, T2> extends BiPredicate<T1, T2>, java.util.function.BiFunction<T1, T2, Boolean> {

    default Boolean apply(T1 t1, T2 t2) {
        return this.test(t1, t2);
    };
}
