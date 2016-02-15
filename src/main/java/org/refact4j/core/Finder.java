package org.refact4j.core;

import java.util.List;

public interface Finder<T, ID, TYPE> {

    List<T> getAll(TYPE type);

    TypeResolver<T, TYPE> getTypeResolver();


}
