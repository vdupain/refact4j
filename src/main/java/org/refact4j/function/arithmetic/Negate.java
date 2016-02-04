package org.refact4j.function.arithmetic;

/**
 * Negate is an arithmetic operator. This operator returns the negative of its
 * numeric argument.
 *
 * @param <T>
 */
public class Negate<T extends Number> extends AbstractArithmeticUnaryOperator<T> {

    @Override
    protected T evaluate(T arg) {
        return this.getArithmeticMath().negates(arg);
    }

}
