package org.refact4j.collection;

import org.refact4j.functor.ForEach;
import org.refact4j.functor.UnaryFunctor;
import org.refact4j.functor.UnaryPredicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class consists exclusively of static methods that operate on or return
 * collections. It contains algorithms that operate on collections.
 */
public final class CollectionHelper {

    private CollectionHelper() {
    }

    /**
     * Applies the given UnaryFunctor to every element over the iterator.
     *
     * @param <T>
     * @param <R>
     * @param iterator     The iterator
     * @param unaryFunctor The unary functor
     * @return
     */
    public static <T, R> R foreach(Iterator<? extends T> iterator, UnaryFunctor<T, R> unaryFunctor) {
        return new ForEach<T, R>(unaryFunctor).eval(iterator);
    }

    /**
     * Filters a collection with a given UnaryPredicate
     *
     * @param <T>
     * @param collection The collection to filter.
     * @param predicate  The predicate to apply for filtering.
     * @return The filtered collection.
     */
    public static <T> Collection<T> filter(Collection<? extends T> collection, UnaryPredicate<T> predicate) {
        Collection<T> result = new ArrayList<T>();
        FilterIterator<T> filterIterator = new FilterIterator<T>(collection.iterator(), predicate);
        for (T t : filterIterator) {
            result.add(t);
        }
        return result;
    }

    public static <T1, T2> Collection<T2> transform(Collection<? extends T1> collection,
                                                    UnaryFunctor<T1, T2> unaryFunctor) {
        Collection<T2> result = new ArrayList<T2>();
        for (T1 t1 : collection) {
            result.add(unaryFunctor.eval(t1));
        }
        return result;
    }

}
