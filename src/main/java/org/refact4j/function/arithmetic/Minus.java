package org.refact4j.function.arithmetic;

/**
 * Minus is an arithmetic operator. This operator returns the difference of two
 * numeric arguments.
 *
 * @param <T>
 */
public class Minus<T extends Number> extends AbstractArithmeticBiFunction<T> {

    @Override
    protected T evaluate(T firstArg, T secondArg) {
        return this.getArithmeticMath().minus(firstArg, secondArg);
    }

}
