package org.refact4j.functor.arithmetic;

public class IntegerArithmeticMath implements ArithmeticMath<Integer> {

    public Integer divides(Integer a, Integer b) {
        return a / b;
    }

    public Integer minus(Integer a, Integer b) {
        return a - b;
    }

    public Integer multiplies(Integer a, Integer b) {
        return a * b;
    }

    public Integer plus(Integer a, Integer b) {
        return a + b;
    }

    public Integer negates(Integer a) {
        return -a;
    }

}
