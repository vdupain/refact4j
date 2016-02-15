package org.refact4j.collection;

import org.refact4j.core.Finder;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Set<T, ID, TYPE> extends HashSet<T> implements Finder<T, ID, TYPE> {


    public T findByIdentifier(ID id) {
        return this.stream().filter(p -> getIdResolver().getId(p).equals(id)).findFirst().get();
    }

    public List<T> getAll(final TYPE type) {
        return this.stream().filter(t -> getTypeResolver().isSameType(type, getTypeResolver().getTypeOf(t)))
                .collect(Collectors.toList());
    }

    @Override
    public IdResolver<T, ID> getIdResolver() {
        return null;
    }

    @Override
    public TypeResolver<T, TYPE> getTypeResolver() {
        return null;
    }

}
