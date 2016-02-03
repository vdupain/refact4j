package org.refact4j.function.aggregate;

import org.refact4j.visitor.Visitable;

abstract class AbstractAggregateVisitor<T> implements AggregateVisitor<T> {

    private T value;

    AbstractAggregateVisitor() {
    }

    public T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

    public void visit(Visitable visitable) {
    }

}
