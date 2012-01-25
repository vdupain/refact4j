package org.refact4j.collection.impl;

import org.refact4j.core.TypeResolver;

public class DefaultTypeResolverImpl<T> implements TypeResolver<T, Class<?>> {

    public Class<?> getTypeOf(T t) {
        return t.getClass();
    }

    public boolean isSameType(Class<?> c1, Class<?> c2) {
        return c1.isAssignableFrom(c2);
    }

}
