package org.refact4j.function.arithmetic;

/**
 * This abstract class provides a skeletal implementation of the
 * AbstractArithmeticFunction interface, to minimize the effort required to
 * implement this interface. To implement an arithmetic unary function, the
 * programmer needs only to extend this class and provide implementation for the
 * apply method.
 *
 * @param <T>
 */
abstract class AbstractArithmeticFunction<T extends Number> implements ArithmeticFunction<T> {

    private final ArithmeticMathHolder<T> arithmeticMathHolder = new ArithmeticMathHolder<>();

    protected abstract T evaluate(T firstArg);

    ArithmeticMath<T> getArithmeticMath() {
        return arithmeticMathHolder.getArithmeticMath();
    }

    @SuppressWarnings("unchecked")
    public T apply(T arg) {
        arithmeticMathHolder.setClazz((Class<T>) (arg != null ? arg.getClass() : null));
        return evaluate(arg);
    }

}
