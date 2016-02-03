package org.refact4j.function.comparison;

import java.util.Comparator;

public class ComparatorComparisonVisitor<T> extends AbstractComparisonVisitor<T> {

    private final Comparator<? super T> comparator;

    public ComparatorComparisonVisitor(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public void visitEqual(Equal<T> equal) {
        setResult(comparator.compare(equal.getFirstArg(), equal.getSecondArg()) == 0);
    }

    public void visitNotEqual(NotEqual<T> equal) {
        setResult(comparator.compare(equal.getFirstArg(), equal.getSecondArg()) != 0);
    }

    public void visitLess(Less<T> equal) {
        setResult(comparator.compare(equal.getFirstArg(), equal.getSecondArg()) < 0);
    }

    public void visitGreater(Greater<T> equal) {
        setResult(comparator.compare(equal.getFirstArg(), equal.getSecondArg()) > 0);
    }

    public void visitLessEqual(LessEqual<T> equal) {
        setResult(comparator.compare(equal.getFirstArg(), equal.getSecondArg()) <= 0);
    }

    public void visitGreaterEqual(GreaterEqual<T> equal) {
        setResult(comparator.compare(equal.getFirstArg(), equal.getSecondArg()) >= 0);
    }

    public void visitMin(Min<T> min) {
        if (comparator.compare(min.getFirstArg(), min.getSecondArg()) <= 0) {
            setValue(min.getFirstArg());
        } else {
            setValue(min.getSecondArg());
        }
    }

    public void visitMax(Max<T> max) {
        if (comparator.compare(max.getFirstArg(), max.getSecondArg()) >= 0) {
            setValue(max.getFirstArg());
        } else {
            setValue(max.getSecondArg());

        }
    }

}
