package org.refact4j.functor.comparison;

import org.refact4j.visitor.Visitable;

abstract class AbstractComparisonVisitor<T> implements ComparisonVisitor<T> {
    private Boolean result;

    private T value;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void visit(Visitable visitable) {
    }

    public abstract void visitEqual(Equal<T> equal);

    public abstract void visitNotEqual(NotEqual<T> notEqual);

    public abstract void visitLess(Less<T> less);

    public abstract void visitGreater(Greater<T> greater);

    public abstract void visitLessEqual(LessEqual<T> lessEqual);

    public abstract void visitGreaterEqual(GreaterEqual<T> greaterEqual);

    public abstract void visitMin(Min<T> min);

    public abstract void visitMax(Max<T> max);

}
