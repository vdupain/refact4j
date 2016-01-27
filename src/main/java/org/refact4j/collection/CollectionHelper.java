package org.refact4j.collection;

import org.refact4j.functor.ForEach;
import org.refact4j.functor.UnaryPredicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * This class consists exclusively of static methods that operate on or return
 * collections. It contains algorithms that operate on collections.
 */
public final class CollectionHelper {

    private CollectionHelper() {
    }

    /**
     * Applies the given Function to every element over the iterator.
     *
     * @param <T>
     * @param <R>
     * @param iterator     The iterator
     * @param function The unary functor
     * @return
     */
    public static void foreach(Iterator iterator, java.util.function.Function function) {
        new ForEach<>(function).apply(iterator);
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
    
}
