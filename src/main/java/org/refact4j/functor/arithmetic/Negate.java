package org.refact4j.functor.arithmetic;

/**
 * Negate is an arithmetic operator. This operator returns the negative of its
 * numeric argument.
 *
 * @param <T>
 */
public class Negate<T extends Number> extends AbstractArithmeticFunction<T> {

    @Override
    protected T evaluate(T arg) {
        return this.getArithmeticMath().negates(arg);
    }

}
