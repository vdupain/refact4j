package org.refact4j.function.aggregate;

import java.util.Collection;

/**
 * AggregateFunctor interface is an unary function that identifies Aggregate
 * functions.
 *
 * @param <T>
 */
public interface AggregateFunctor<T> extends java.util.function.Function<Collection<? extends T>, T> {

}
