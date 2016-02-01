package org.refact4j.core;

import org.refact4j.collection.DefaultIdResolver;
import org.refact4j.collection.DefaultTypeResolver;
import org.refact4j.functor.UnaryPredicate;

import java.util.List;

public interface Finder<T, ID, TYPE> {

    T findByIdentifier(ID id);

    T findByIdentifier(TYPE type, ID id);

    List<T> getAll(TYPE type);

    default IdResolver<T, ID> getIdResolver() {
        return new DefaultIdResolver();
    }

    default TypeResolver<T, TYPE> getTypeResolver() {
        return new DefaultTypeResolver();
    }


}
