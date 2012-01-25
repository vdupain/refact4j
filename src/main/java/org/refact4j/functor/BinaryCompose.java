package org.refact4j.functor;

import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

/**
 * BinaryCompose is a Unary Functor that passes the results of two Unary
 * Functors as the arguments to a Binary Functor. This allows for the
 * construction of compound functors from the primitives found in the
 * arithmetic, logical, and comparison packages. This functor is a
 * "y=f(g(x),h(x))" function.
 *
 * @param <F1>
 * @param <F2>
 * @param <T>
 * @param <R>
 */
public class BinaryCompose<F1, F2, T, R> implements UnaryFunctor<T, R>, Visitable {

    private final UnaryFunctor<T, F1> firstUnaryFunctor;

    private final UnaryFunctor<T, F2> secondUnaryFunctor;

    private final BinaryFunctor<F1, F2, R> binaryFunctor;

    public BinaryCompose(BinaryFunctor<F1, F2, R> binaryFunctor, UnaryFunctor<T, F1> firstUnaryFunctor,
                         UnaryFunctor<T, F2> secondUnaryFunctor) {
        this.binaryFunctor = binaryFunctor;
        this.firstUnaryFunctor = firstUnaryFunctor;
        this.secondUnaryFunctor = secondUnaryFunctor;
    }

    /**
     * f(g(x),h(x))
     */
    public R eval(T arg) {
        return binaryFunctor.eval(firstUnaryFunctor.eval(arg), secondUnaryFunctor.eval(arg));
    }

    public BinaryFunctor<F1, F2, R> getBinaryFunctor() {
        return binaryFunctor;
    }

    public UnaryFunctor<T, F1> getFirstUnaryFunctor() {
        return firstUnaryFunctor;
    }

    public UnaryFunctor<T, F2> getSecondUnaryFunctor() {
        return secondUnaryFunctor;
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
