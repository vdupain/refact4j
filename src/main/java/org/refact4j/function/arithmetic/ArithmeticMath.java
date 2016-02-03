package org.refact4j.function.arithmetic;

public interface ArithmeticMath<T extends Number> {

    T plus(T a, T b);

    T minus(T a, T b);

    T multiplies(T a, T b);

    T divides(T a, T b);

    T negates(T a);

}
