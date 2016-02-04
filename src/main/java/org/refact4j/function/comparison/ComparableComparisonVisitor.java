package org.refact4j.function.comparison;

import org.refact4j.function.AbstractBiPredicate;
import org.refact4j.util.ComparatorHelper;

public class ComparableComparisonVisitor<T extends Object & Comparable<? super T>> extends AbstractComparisonVisitor<T> {

    public void visitEqual(Equal<T> equal) {
        setResult(compare(equal) == 0);
    }

    public void visitLess(Less<T> less) {
        setResult(compare(less) < 0);
    }

    public void visitGreater(Greater<T> greater) {
        setResult(compare(greater) > 0);
    }

    public void visitLessEqual(LessEqual<T> lessEqual) {
        setResult(compare(lessEqual) <= 0);
    }

    public void visitGreaterEqual(GreaterEqual<T> greaterEqual) {
        setResult(compare(greaterEqual) >= 0);
    }

    public void visitMin(Min<T> min) {
        if ((min.getFirstArg()).compareTo(min.getSecondArg()) <= 0) {
            setValue(min.getFirstArg());
        } else {
            setValue(min.getSecondArg());
        }
    }

    public void visitMax(Max<T> max) {
        if ((max.getFirstArg()).compareTo(max.getSecondArg()) >= 0) {
            setValue(max.getFirstArg());
        } else {
            setValue(max.getSecondArg());

        }
    }

    private int compare(AbstractBiPredicate<T, T> binaryPredicate) {
        return ComparatorHelper.compare(binaryPredicate.getFirstArg(), binaryPredicate.getSecondArg());
    }
}
