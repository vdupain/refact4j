package org.refact4j.function;

import org.refact4j.visitor.Visitor;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CompositeUnaryPredicate<T> implements UnaryPredicate<T> {
    private BinaryCompose<?, ?, T, Boolean> compositeFunctor;

    private BiFunction<?, ?, Boolean> biFunction;

    private java.util.function.Function<T, ?> function;
    private Object constant;


    public CompositeUnaryPredicate(BiFunction<?, ?, Boolean> biFunction, Function<T, ?> function,
                                   Object constant) {
        this.biFunction = biFunction;
        this.function = function;
        this.constant = constant;
        this.compositeFunctor = new BinaryCompose(biFunction, function, Functions.constant(constant));
    }

    public boolean test(T arg) {
        return this.compositeFunctor.apply(arg);
    }

    public BiFunction<?, ?, Boolean> getBiFunction() {
        return biFunction;
    }

    public java.util.function.Function<T, ?> getFunction() {
        return function;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof CompositeUnaryPredicateVisitor) {
            ((CompositeUnaryPredicateVisitor) visitor).visitCompositeUnaryPredicate(this);
        }
    }

    public Object getConstant() {
        return constant;
    }

    public interface CompositeUnaryPredicateVisitor extends Visitor {
        void visitCompositeUnaryPredicate(CompositeUnaryPredicate<?> unaryPredicate);
    }

}
