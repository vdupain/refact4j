package org.refact4j.function.arithmetic;

public class DoubleArithmeticMath implements ArithmeticMath<Double> {

    public Double divides(Double a, Double b) {
        return a / b;
    }

    public Double minus(Double a, Double b) {
        return a - b;
    }

    public Double multiplies(Double a, Double b) {
        return a * b;
    }

    public Double plus(Double a, Double b) {
        return a + b;
    }

    public Double negates(Double a) {
        return -a;
    }

}
