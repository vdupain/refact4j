package org.refact4j.core;

import org.refact4j.functor.UnaryPredicate;

import java.util.List;

public interface Finder<T, ID, TYPE> {

    T findByIdentifier(ID id);

    T findByIdentifier(TYPE type, ID id);

    List<T> findByPredicate(UnaryPredicate<T> predicate);

    T findUnique(TYPE type, UnaryPredicate<T> predicate);

    List<T> getAll(TYPE type);

    List<T> getAll(TYPE type, UnaryPredicate<T> predicate);

}
