package org.refact4j.functor.aggregate;

import java.util.Collection;

/**
 * AggregateFunctor interface is an unary functor that identifies Aggregate
 * functions.
 *
 * @param <T>
 */
public interface AggregateFunctor<T> extends java.util.function.Function<Collection<? extends T>, T> {

}
