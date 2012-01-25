package org.refact4j.functor;

import org.refact4j.visitor.Visitor;

import java.util.Iterator;

public class ForEach<T, R> extends AbstractUnaryFunctor<Iterator<? extends T>, R> {
    private UnaryFunctor<T, R> functor;

    public ForEach() {
    }

    public ForEach(UnaryFunctor<T, R> functor) {
        this.functor = functor;
    }

    @Override
    protected R evaluate(Iterator<? extends T> iterator) {
        R value = null;
        while (iterator.hasNext()) {
            value = functor.eval(iterator.next());
        }
        return value;
    }

    public UnaryFunctor<T, R> getFunctor() {
        return functor;
    }

    public void setFunctor(UnaryFunctor<T, R> functor) {
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
