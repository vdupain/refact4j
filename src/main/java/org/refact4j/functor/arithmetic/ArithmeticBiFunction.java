package org.refact4j.functor.arithmetic;

/**
 * ArithmeticBiFunction is an interface for arithmetic operator with two
 * operands. ArithmeticBiFunction is a binary functor.
 *
 * @param <T>
 */
public interface ArithmeticBiFunction<T extends Number> extends java.util.function.BiFunction<T, T, T> {
}
