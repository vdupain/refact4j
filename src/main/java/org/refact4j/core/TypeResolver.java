package org.refact4j.core;

public interface TypeResolver<T, TYPE> {

    TYPE getTypeOf(T t);

    boolean isSameType(TYPE t1, TYPE t2);
}
