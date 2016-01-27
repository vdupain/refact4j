package org.refact4j.functor;

import org.refact4j.visitor.Visitor;

import java.util.function.BiFunction;

public class CompositeUnaryPredicate<T> extends AbstractUnaryPredicate<T> {
    private BinaryCompose<?, ?, T, Boolean> compositeFunctor;

    private BiFunction<?, ?, Boolean> biFunction;

    private java.util.function.Function<T,?> function;

    private ConstantFunction<?> constantUnaryFunctor;

    public CompositeUnaryPredicate(BiFunction<?, ?, Boolean> biFunction, java.util.function.Function<T,?> function,
                                   Object constant) {
        init(biFunction, function, new ConstantFunction<Object>(constant));

    }

    public CompositeUnaryPredicate(BiFunction<?, ?, Boolean> biFunction, java.util.function.Function<T,?> function,
                                   ConstantFunction<?> constantUnaryFunctor) {
        init(biFunction, function, constantUnaryFunctor);
    }

    private void init(BiFunction<?, ?, Boolean> biFunction, java.util.function.Function<T,?> function,
                      ConstantFunction<?> constantUnaryFunctor) {
        this.biFunction = biFunction;
        this.function = function;
        this.constantUnaryFunctor = constantUnaryFunctor;
        this.compositeFunctor = new BinaryCompose(biFunction, function, constantUnaryFunctor);
    }

    public boolean evaluate(T arg) {
        return this.compositeFunctor.apply(arg);
    }

    public BiFunction<?, ?, Boolean> getBiFunction() {
        return biFunction;
    }

    public ConstantFunction<?> getConstantUnaryFunctor() {
        return constantUnaryFunctor;
    }

    public java.util.function.Function<T,?> getFunction() {
        return function;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof CompositeUnaryPredicateVisitor) {
            ((CompositeUnaryPredicateVisitor) visitor).visitCompositeUnaryPredicate(this);
        }
    }

    public interface CompositeUnaryPredicateVisitor extends Visitor {
        void visitCompositeUnaryPredicate(CompositeUnaryPredicate<?> unaryPredicate);
    }

}
