package org.refact4j.functor.arithmetic;

/**
 * This abstract class provides a skeletal implementation of the
 * AbstractArithmeticUnaryFunctor interface, to minimize the effort required to
 * implement this interface. To implement an arithmetic unary functor, the
 * programmer needs only to extend this class and provide implementation for the
 * eval method.
 *
 * @param <T>
 */
public abstract class AbstractArithmeticUnaryFunctor<T extends Number> implements ArithmeticUnaryFunctor<T> {

    private final ArithmeticMathHolder<T> arithmeticMathHolder = new ArithmeticMathHolder<T>();

    protected abstract T evaluate(T firstArg);

    ArithmeticMath<T> getArithmeticMath() {
        return arithmeticMathHolder.getArithmeticMath();
    }

    @SuppressWarnings("unchecked")
    public T eval(T arg) {
        arithmeticMathHolder.setClazz((Class<T>) (arg != null ? arg.getClass() : null));
        return evaluate(arg);
    }

}
