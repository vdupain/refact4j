package org.refact4j.core;

import java.util.List;

public interface Finder<T, ID, TYPE> {

    T findByIdentifier(ID id);

    List<T> getAll(TYPE type);

    IdResolver<T, ID> getIdResolver();

    TypeResolver<T, TYPE> getTypeResolver();


}
