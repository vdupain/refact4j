package org.refact4j.functor.arithmetic;

import java.util.HashMap;
import java.util.Map;

public final class ArithmeticMathFactory {
    private static final ArithmeticMathFactory INSTANCE = new ArithmeticMathFactory();

    static {
        INSTANCE.repo.put(Integer.class, new IntegerArithmeticMath());
        INSTANCE.repo.put(Double.class, new DoubleArithmeticMath());
    }

    private final Map<Class<? extends Number>, ArithmeticMath<? extends Number>> repo = new HashMap<Class<? extends Number>, ArithmeticMath<? extends Number>>();

    private ArithmeticMathFactory() {
    }

    public static ArithmeticMathFactory getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <T extends Number> ArithmeticMath<T> getArithmeticMath(Class<T> clazz) {
        return (ArithmeticMath<T>) repo.get(clazz);
    }

}
