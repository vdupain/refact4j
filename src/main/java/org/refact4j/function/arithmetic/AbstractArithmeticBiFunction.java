package org.refact4j.function.arithmetic;

/**
 * This abstract class provides a skeletal implementation of the
 * AbstractArithmeticBiFunction interface, to minimize the effort required to
 * implement this interface. To implement an arithmetic binary function, the
 * programmer needs only to extend this class and provide implementation for the
 * apply method.
 *
 * @param <T>
 */
abstract class AbstractArithmeticBiFunction<T extends Number> implements ArithmeticBiFunction<T> {

    private final ArithmeticMathHolder<T> arithmeticMathHolder = new ArithmeticMathHolder<>();

    protected abstract T evaluate(T firstArg, T secondArg);

    ArithmeticMath<T> getArithmeticMath() {
        return arithmeticMathHolder.getArithmeticMath();
    }

    @SuppressWarnings("unchecked")
    public T apply(T firstArg, T secondArg) {
        this.arithmeticMathHolder.setClazz((Class<T>) (firstArg != null ? firstArg.getClass() : null));
        if (arithmeticMathHolder.getClazz() == null) {
            this.arithmeticMathHolder.setClazz((Class<T>) (secondArg != null ? secondArg.getClass() : null));
        }
        return evaluate(firstArg, secondArg);
    }

}
