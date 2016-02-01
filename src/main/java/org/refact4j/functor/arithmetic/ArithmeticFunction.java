package org.refact4j.functor.arithmetic;

/**
 * ArithmeticFunction is an interface for arithmetic operator with one
 * operand. ArithmeticFunction is an unary functor.
 *
 * @param <T>
 */
interface ArithmeticFunction<T extends Number> extends java.util.function.Function<T, T> {
}
