package org.refact4j.function.aggregate;

import org.refact4j.visitor.Visitor;

import java.util.Comparator;

/**
 * MinValue is an aggregate function that identifies the smallest value in a
 * collection.
 *
 * @param <T>
 */
public class MinValue<T> extends AbstractAggregateFunctor<T> {

    public MinValue() {
        super(null);
    }

    public MinValue(Comparator<T> comparator) {
        super(comparator);
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof MinValueVisitor) {
            ((MinValueVisitor) visitor).visitMinValue(this);
        }
    }

    public interface MinValueVisitor<T> extends Visitor {
        void visitMinValue(MinValue<T> minValue);
    }

}
