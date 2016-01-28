package org.refact4j.functor.arithmetic;

class ArithmeticMathHolder<T extends Number> {
    private ArithmeticMath<T> arithmeticMath;
    private Class<T> clazz;

    ArithmeticMath<T> getArithmeticMath() {
        return this.getArithmeticMath(clazz);
    }

    @SuppressWarnings("unchecked")
    ArithmeticMath<T> getArithmeticMath(Class<? extends Number> clazz) {
        if (arithmeticMath == null) {
            arithmeticMath = (ArithmeticMath<T>) ArithmeticMathFactory.getInstance().getArithmeticMath(clazz);
        }
        return arithmeticMath;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

}
