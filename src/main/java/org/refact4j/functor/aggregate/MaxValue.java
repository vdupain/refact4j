package org.refact4j.functor.aggregate;

import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * MaxValue is an aggregate functor that identifies the largest value in a
 * collection.
 *
 * @param <T>
 */
public class MaxValue<T> extends AbstractAggregateFunctor<T> {

    public MaxValue() {
        super(null);
    }

    public MaxValue(Comparator<T> comparator) {
        super(comparator);
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof MaxValueVisitor) {
            ((MaxValueVisitor) visitor).visitMaxValue(this);
        }
    }

    public interface MaxValueVisitor<T> extends Visitor {
        void visitMaxValue(MaxValue<T> maxValue);
    }

}
