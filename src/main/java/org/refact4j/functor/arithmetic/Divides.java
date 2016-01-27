package org.refact4j.functor.arithmetic;

/**
 * Divides is an arithmetic operator. This operator returns the quotient of two
 * numeric arguments.
 *
 * @param <T>
 */
public class Divides<T extends Number> extends AbstractArithmeticBiFunction<T> {

    @Override
    protected T evaluate(T firstArg, T secondArg) {
        return this.getArithmeticMath().divides(firstArg, secondArg);
    }

}
