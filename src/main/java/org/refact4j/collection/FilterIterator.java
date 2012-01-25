package org.refact4j.collection;

import org.refact4j.functor.UnaryPredicate;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is a decorator for an iterator such that only elements matching a
 * predicate filter are returned.
 *
 * @see UnaryPredicate
 */
public class FilterIterator<T> implements Iterator<T>, Iterable<T> {
    private Iterator<? extends T> iterator;

    private UnaryPredicate<T> predicate;

    private T nextObject;

    private boolean nextObjectSet;

    public FilterIterator(Iterator<? extends T> iterator, UnaryPredicate<T> predicate) {
        super();
        this.iterator = iterator;
        this.predicate = predicate;
        init();
    }

    public boolean hasNext() {
        if (nextObjectSet) {
            return true;
        }
        return setNextObject();
    }

    public T next() {
        if (!nextObjectSet && !setNextObject()) {
            throw new NoSuchElementException();

        }
        nextObjectSet = false;
        return nextObject;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    private boolean setNextObject() {
        while (iterator.hasNext()) {
            T object = iterator.next();
            if (predicate.eval(object)) {
                nextObject = object;
                nextObjectSet = true;
                return true;
            }
        }
        return false;
    }

    public void setIterator(Iterator<T> iterator) {
        this.iterator = iterator;
        init();
    }

    public void setUnaryPredicate(UnaryPredicate<T> predicate) {
        this.predicate = predicate;
        init();
    }

    private void init() {
        nextObject = null;
        nextObjectSet = false;
    }

    public Iterator<T> iterator() {
        return this;
    }

}
