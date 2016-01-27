package org.refact4j.functor.arithmetic;

/**
 * This abstract class provides a skeletal implementation of the
 * AbstractArithmeticBinaryFunctor interface, to minimize the effort required to
 * implement this interface. To implement an arithmetic binary functor, the
 * programmer needs only to extend this class and provide implementation for the
 * apply method.
 *
 * @param <T>
 */
public abstract class AbstractArithmeticBinaryFunctor<T extends Number> implements ArithmeticBinaryFunctor<T> {

    private final ArithmeticMathHolder<T> arithmeticMathHolder = new ArithmeticMathHolder<T>();

    protected abstract T evaluate(T firstArg, T secondArg);

    ArithmeticMath<T> getArithmeticMath() {
        return arithmeticMathHolder.getArithmeticMath();
    }

    @SuppressWarnings("unchecked")
    public T eval(T firstArg, T secondArg) {
        this.arithmeticMathHolder.setClazz((Class<T>) (firstArg != null ? firstArg.getClass() : null));
        if (arithmeticMathHolder.getClazz() == null) {
            this.arithmeticMathHolder.setClazz((Class<T>) (secondArg != null ? secondArg.getClass() : null));
        }
        return evaluate(firstArg, secondArg);
    }

}
