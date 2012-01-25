package org.refact4j.collection;

import org.refact4j.core.Finder;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;
import org.refact4j.functor.UnaryFunctor;

import java.io.Serializable;

public interface Collection<T, ID extends Serializable, TYPE> extends java.util.Collection<T>, Finder<T, ID, TYPE> {

    void apply(TYPE type, UnaryFunctor<T, ?> functor);

    TypeResolver<T, TYPE> getTypeResolver();

    IdResolver<T, ID> getIdResolver();

    ChangeSet<T> getChangeSet();
}
