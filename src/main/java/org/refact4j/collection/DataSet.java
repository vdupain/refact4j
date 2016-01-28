package org.refact4j.collection;

import org.refact4j.core.Finder;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;

import java.io.Serializable;

public interface DataSet<T, ID extends Serializable, TYPE> extends java.util.Set<T>, Finder<T, ID, TYPE> {

    TypeResolver<T, TYPE> getTypeResolver();

    IdResolver<T, ID> getIdResolver();

}
