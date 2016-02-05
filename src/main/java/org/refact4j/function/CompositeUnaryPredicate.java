package org.refact4j.function;

import org.refact4j.visitor.Visitor;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CompositeUnaryPredicate<T> implements UnaryPredicate<T> {
    private final BinaryCompose<?, ?, T, Boolean> compositeFunctor;

    private final BiFunction<?, ?, Boolean> biFunction;

    private final java.util.function.Function<T, ?> function;
    private final Object value;


    public CompositeUnaryPredicate(BiFunction<?, ?, Boolean> biFunction, Function<T, ?> function,
                                   Object value) {
        this.biFunction = biFunction;
        this.function = function;
        this.value = value;
        this.compositeFunctor = new BinaryCompose(biFunction, function, Functions.constant(value));
    }

    public boolean test(T arg) {
        return this.compositeFunctor.apply(arg);
    }

    public BiFunction<?, ?, Boolean> getBiFunction() {
        return biFunction;
    }

    public Function<T, ?> getFunction() {
        return function;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof CompositeUnaryPredicateVisitor) {
            ((CompositeUnaryPredicateVisitor) visitor).visitCompositeUnaryPredicate(this);
        }
    }

    public Object getValue() {
        return value;
    }

    public interface CompositeUnaryPredicateVisitor extends Visitor {
        void visitCompositeUnaryPredicate(CompositeUnaryPredicate<?> unaryPredicate);
    }

}
