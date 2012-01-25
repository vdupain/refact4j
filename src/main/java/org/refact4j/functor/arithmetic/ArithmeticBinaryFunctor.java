package org.refact4j.functor.arithmetic;

import org.refact4j.functor.BinaryFunctor;

/**
 * ArithmeticBinaryFunctor is an interface for arithmetic operator with two
 * operands. ArithmeticBinaryFunctor is a binary functor.
 *
 * @param <T>
 */
public interface ArithmeticBinaryFunctor<T extends Number> extends BinaryFunctor<T, T, T> {
}
