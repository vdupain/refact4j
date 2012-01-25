package org.refact4j.collection.impl;

import org.refact4j.collection.DataSet;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;

import java.io.Serializable;

public class DefaultDataSetImpl<T> extends AbstractListImpl<T, Serializable, Class<?>> implements
        DataSet<T, Serializable, Class<?>> {

    private static final long serialVersionUID = -4146887451732819925L;

    public IdResolver<T, Serializable> getIdResolver() {
        return new HashCodeIdResolver();
    }

    public TypeResolver<T, Class<?>> getTypeResolver() {
        return new DefaultTypeResolverImpl<T>();
    }

}
