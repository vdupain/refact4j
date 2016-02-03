package org.refact4j.function;

import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * BinaryCompose is a Unary Functor that passes the results of two Unary
 * Functors as the arguments to a Binary Functor. This allows for the
 * construction of compound functors from the primitives found in the
 * arithmetic, logical, and comparison packages. This function is a
 * "y=f(g(x),h(x))" function.
 *
 * @param <F1>
 * @param <F2>
 * @param <T>
 * @param <R>
 */
public class BinaryCompose<F1, F2, T, R> implements Visitable, Function<T, R> {
    private final java.util.function.Function<T, F1> firstFunction;
    private final java.util.function.Function<T, F2> secondFunction;
    private final BiFunction<F1, F2, R> biFunction;

    public BinaryCompose(BiFunction<F1, F2, R> biFunction, Function<T, F1> firstFunction,
                         java.util.function.Function<T, F2> secondFunction) {
        this.biFunction = biFunction;
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    /**
     * f(g(x),h(x))
     */
    public R apply(T arg) {
        return biFunction.apply(firstFunction.apply(arg), secondFunction.apply(arg));
    }

    public BiFunction<F1, F2, R> getBiFunction() {
        return biFunction;
    }

    public Function<T, F1> getFirstFunction() {
        return firstFunction;
    }

    public Function<T, F2> getSecondFunction() {
        return secondFunction;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof BinaryComposeVisitor) {
            ((BinaryComposeVisitor) visitor).visitBinaryCompose(this);
        }
    }

    public interface BinaryComposeVisitor extends Visitor {
        void visitBinaryCompose(BinaryCompose<?, ?, ?, ?> binaryCompose);
    }

}
