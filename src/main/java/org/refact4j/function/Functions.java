package org.refact4j.function;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class Functions {
    private Functions() {
        throw new AssertionError();
    }

    public static <T, U, R> Function<U, R> bind1st(BiFunction<T, U, R> function, T first) {
        return s -> function.apply(first,s);
    }

    public static <R,T, U> Function<T, R> bind2nd(BiFunction<T, U, R> function, U second) {
        return s -> function.apply(s, second);
    }


}

