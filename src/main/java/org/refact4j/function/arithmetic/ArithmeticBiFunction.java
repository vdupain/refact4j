package org.refact4j.function.arithmetic;

/**
 * ArithmeticBiFunction is an interface for arithmetic operator with two
 * operands. ArithmeticBiFunction is a binary function.
 *
 * @param <T>
 */
interface ArithmeticBiFunction<T extends Number> extends java.util.function.BiFunction<T, T, T> {
}
