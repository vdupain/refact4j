package org.refact4j.functor.arithmetic;

import org.refact4j.functor.BiFunction;

/**
 * ArithmeticBiFunction is an interface for arithmetic operator with two
 * operands. ArithmeticBiFunction is a binary functor.
 *
 * @param <T>
 */
public interface ArithmeticBiFunction<T extends Number> extends BiFunction<T, T, T> {
}
