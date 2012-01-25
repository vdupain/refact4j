package org.refact4j.collection.impl;

import org.refact4j.core.IdResolver;
import org.refact4j.core.Identifiable;
import org.refact4j.core.TypeResolver;

import java.io.Serializable;

public class SetImpl extends AbstractSetImpl<Identifiable<Serializable>, Serializable, Class<?>> {

    private static final long serialVersionUID = 8720633848397047336L;

    public IdResolver<Identifiable<Serializable>, Serializable> getIdResolver() {
        return new DefaultIdResolver<Serializable>();
    }

    public TypeResolver<Identifiable<Serializable>, Class<?>> getTypeResolver() {
        return new DefaultTypeResolverImpl<Identifiable<Serializable>>();
    }

}
