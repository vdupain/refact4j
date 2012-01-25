package org.refact4j.functor.arithmetic;

import org.refact4j.functor.UnaryFunctor;

/**
 * ArithmeticUnaryFunctor is an interface for arithmetic operator with one
 * operand. ArithmeticUnaryFunctor is an unary functor.
 *
 * @param <T>
 */
public interface ArithmeticUnaryFunctor<T extends Number> extends UnaryFunctor<T, T> {
}
