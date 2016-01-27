package org.refact4j.functor;

import org.refact4j.visitor.Visitor;

public class CompositeUnaryPredicate<T> extends AbstractUnaryPredicate<T> {
    private BinaryCompose<?, ?, T, Boolean> compositeFunctor;

    private BinaryFunctor<?, ?, Boolean> binaryFunctor;

    private java.util.function.Function<T,?> function;

    private ConstantFunction<?> constantUnaryFunctor;

    public CompositeUnaryPredicate(BinaryFunctor<?, ?, Boolean> binaryFunctor, java.util.function.Function<T,?> function,
                                   Object constant) {
        init(binaryFunctor, function, new ConstantFunction<Object>(constant));

    }

    public CompositeUnaryPredicate(BinaryFunctor<?, ?, Boolean> binaryFunctor, java.util.function.Function<T,?> function,
                                   ConstantFunction<?> constantUnaryFunctor) {
        init(binaryFunctor, function, constantUnaryFunctor);
    }

    private void init(BinaryFunctor<?, ?, Boolean> binaryFunctor, java.util.function.Function<T,?> function,
                      ConstantFunction<?> constantUnaryFunctor) {
        this.binaryFunctor = binaryFunctor;
        this.function = function;
        this.constantUnaryFunctor = constantUnaryFunctor;
        this.compositeFunctor = new BinaryCompose(binaryFunctor, function, constantUnaryFunctor);
    }

    public Boolean evaluate(T arg) {
        return this.compositeFunctor.apply(arg);
    }

    public BinaryFunctor<?, ?, Boolean> getBinaryFunctor() {
        return binaryFunctor;
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
