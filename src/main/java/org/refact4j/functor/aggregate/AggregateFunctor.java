package org.refact4j.functor.aggregate;

import org.refact4j.functor.UnaryFunctor;

import java.util.Collection;

/**
 * AggregateFunctor interface is an unary functor that identifies Aggregate
 * functions.
 *
 * @param <T>
 */
public interface AggregateFunctor<T> extends UnaryFunctor<Collection<? extends T>, T> {

}
