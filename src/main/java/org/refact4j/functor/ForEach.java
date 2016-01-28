package org.refact4j.functor;

import org.refact4j.visitor.Visitor;

import java.util.Iterator;

public class ForEach<T, R> extends AbstractFunction<Iterator<? extends T>, R> {
    private java.util.function.Function<T, R> functor;

    public ForEach() {
    }

    public ForEach(java.util.function.Function<T, R> functor) {
        this.functor = functor;
    }

    @Override
    protected R evaluate(Iterator<? extends T> iterator) {
        R value = null;
        while (iterator.hasNext()) {
            value = functor.apply(iterator.next());
        }
        return value;
    }

    public java.util.function.Function<T, R> getFunctor() {
        return functor;
    }

    public void setFunctor(java.util.function.Function<T, R> functor) {
        this.functor = functor;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof ForEachVisitor) {
            ((ForEachVisitor) visitor).visitForEach(this);
        }
    }

    public interface ForEachVisitor extends Visitor {
        void visitForEach(ForEach forEach);
    }

}
