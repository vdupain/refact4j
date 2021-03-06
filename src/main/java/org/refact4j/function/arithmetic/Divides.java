package org.refact4j.function.arithmetic;

/**
 * Divides is an arithmetic operator. This operator returns the quotient of two
 * numeric arguments.
 *
 * @param <T>
 */
public class Divides<T extends Number> extends AbstractArithmeticBinaryOperator<T> {

    @Override
    protected T evaluate(T firstArg, T secondArg) {
        return this.getArithmeticMath().divides(firstArg, secondArg);
    }

}
