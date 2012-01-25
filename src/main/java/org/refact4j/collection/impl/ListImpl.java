package org.refact4j.collection.impl;

import org.refact4j.core.IdResolver;
import org.refact4j.core.Identifiable;
import org.refact4j.core.TypeResolver;

import java.io.Serializable;

public class ListImpl extends AbstractListImpl<Identifiable<Serializable>, Serializable, Class<?>> {

    private static final long serialVersionUID = 6861990109130790613L;

    public IdResolver<Identifiable<Serializable>, Serializable> getIdResolver() {
        return new DefaultIdResolver<Serializable>();
    }

    public TypeResolver<Identifiable<Serializable>, Class<?>> getTypeResolver() {
        return new DefaultTypeResolverImpl<Identifiable<Serializable>>();
    }

}
