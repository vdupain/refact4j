package org.refact4j.function.aggregate;

import org.refact4j.function.aggregate.MaxValue.MaxValueVisitor;
import org.refact4j.function.aggregate.MinValue.MinValueVisitor;

interface AggregateVisitor<T> extends MinValueVisitor<T>, MaxValueVisitor<T> {

    T getValue();

}
