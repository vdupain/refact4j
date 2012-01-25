package org.refact4j.functor;

import org.refact4j.visitor.Visitor;

public class CompositeUnaryPredicate<T> extends AbstractUnaryPredicate<T> {
    private BinaryCompose<?, ?, T, Boolean> compositeFunctor;

    private BinaryFunctor<?, ?, Boolean> binaryFunctor;

    private UnaryFunctor<T, ?> unaryFunctor;

    private ConstantUnaryFunctor<?> constantUnaryFunctor;

    public CompositeUnaryPredicate(BinaryFunctor<?, ?, Boolean> binaryFunctor, UnaryFunctor<T, ?> unaryFunctor,
                                   Object constant) {
        init(binaryFunctor, unaryFunctor, new ConstantUnaryFunctor<Object>(constant));

    }

    public CompositeUnaryPredicate(BinaryFunctor<?, ?, Boolean> binaryFunctor, UnaryFunctor<T, ?> unaryFunctor,
                                   ConstantUnaryFunctor<?> constantUnaryFunctor) {
        init(binaryFunctor, unaryFunctor, constantUnaryFunctor);
    }

    private void init(BinaryFunctor<?, ?, Boolean> binaryFunctor, UnaryFunctor<T, ?> unaryFunctor,
                      ConstantUnaryFunctor<?> constantUnaryFunctor) {
        this.binaryFunctor = binaryFunctor;
        this.unaryFunctor = unaryFunctor;
        this.constantUnaryFunctor = constantUnaryFunctor;
        this.compositeFunctor = new BinaryCompose(binaryFunctor, unaryFunctor, constantUnaryFunctor);
    }

    public Boolean evaluate(T arg) {
        return this.compositeFunctor.eval(arg);
    }

    public BinaryFunctor<?, ?, Boolean> getBinaryFunctor() {
        return binaryFunctor;
    }

    public ConstantUnaryFunctor<?> getConstantUnaryFunctor() {
        return constantUnaryFunctor;
    }

    public UnaryFunctor<T, ?> getUnaryFunctor() {
        return unaryFunctor;
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
