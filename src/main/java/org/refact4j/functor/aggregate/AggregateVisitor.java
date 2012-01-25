package org.refact4j.functor.aggregate;

import org.refact4j.functor.aggregate.MaxValue.MaxValueVisitor;
import org.refact4j.functor.aggregate.MinValue.MinValueVisitor;

interface AggregateVisitor<T> extends MinValueVisitor<T>, MaxValueVisitor<T> {

    T getValue();

}
