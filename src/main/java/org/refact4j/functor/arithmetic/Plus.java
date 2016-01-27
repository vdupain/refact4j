package org.refact4j.functor.arithmetic;

/**
 * Plus is an arithmetic operator. This operator returns the sum of two numeric
 * arguments.
 *
 * @param <T>
 */
public class Plus<T extends Number> extends AbstractArithmeticBiFunction<T> {

    @Override
    protected T evaluate(T firstArg, T secondArg) {
        return this.getArithmeticMath().plus(firstArg, secondArg);
    }

}
