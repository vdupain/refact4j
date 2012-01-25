package org.refact4j.functor.arithmetic;

/**
 * Multiplies is an arithmetic operator. This operator returns the product of
 * two numeric arguments.
 *
 * @param <T>
 */
public class Multiplies<T extends Number> extends AbstractArithmeticBinaryFunctor<T> {

    @Override
    protected T evaluate(T firstArg, T secondArg) {
        return this.getArithmeticMath().multiplies(firstArg, secondArg);
    }

}
