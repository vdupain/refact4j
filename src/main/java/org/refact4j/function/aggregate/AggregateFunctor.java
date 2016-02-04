package org.refact4j.function.aggregate;

import java.util.Collection;
import java.util.function.Function;

/**
 * AggregateFunctor interface is an unary function that identifies Aggregate
 * functions.
 *
 * @param <T>
 */
public interface AggregateFunctor<T> extends Function<Collection<? extends T>, T> {

}
