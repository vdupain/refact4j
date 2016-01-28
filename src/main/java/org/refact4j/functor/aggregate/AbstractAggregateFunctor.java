package org.refact4j.functor.aggregate;

import org.refact4j.functor.AbstractFunction;
import org.refact4j.visitor.Visitor;

import java.util.Collection;
import java.util.Comparator;

/**
 * This abstract class provides a skeletal implementation of the
 * AggregateFunctor interface, to minimize the effort required to implement this
 * interface. To implement an aggregate functor, the programmer needs only to
 * extend this class and provide implementation for the test method.
 *
 * @param <T>
 */
public abstract class AbstractAggregateFunctor<T> extends AbstractFunction<Collection<? extends T>, T> implements
        AggregateFunctor<T> {
    private AggregateVisitor<T> visitor;
    
    AbstractAggregateFunctor(Comparator<? extends T> comparator) {
        setComparator(comparator);
    }

    public void setComparator(Comparator<? extends T> comparator) {
        if (comparator == null) {
            this.visitor = new ComparableAggregateVisitor();
        } else {
            this.visitor = new ComparatorAggregateVisitor(comparator);
        }
    }

    protected T evaluate(Collection<? extends T> arg) {
        this.accept(this.visitor);
        return visitor.getValue();
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
