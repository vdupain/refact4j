package org.refact4j.core;

public interface IdResolver<T, ID> {

    ID getId(T t);
}
