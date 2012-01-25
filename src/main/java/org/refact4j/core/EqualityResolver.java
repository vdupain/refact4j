package org.refact4j.core;

public interface EqualityResolver<T1, T2> {

    boolean areEquals(T1 t1, T2 t2);
}
