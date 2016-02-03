package org.refact4j.function.comparison;

import org.refact4j.visitor.Visitable;

abstract class AbstractComparisonVisitor<T> implements ComparisonVisitor<T> {
    private Boolean result;

    private T value;

    public Boolean getResult() {
        return result;
    }

    void setResult(Boolean result) {
        this.result = result;
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
