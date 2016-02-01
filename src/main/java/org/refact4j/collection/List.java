package org.refact4j.collection;

import org.refact4j.core.Finder;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class List<T, ID, TYPE> extends ArrayList<T> implements
        Finder<T, ID, TYPE> {


    public T findByIdentifier(ID id) {
        return this.stream().filter(p -> getIdResolver().getId(p).equals(id)).findFirst().get();
    }

    public T findByIdentifier(TYPE type, ID id) {
        return this.getAll(type).stream().filter(p -> getIdResolver().getId(p).equals(id)).findFirst().get();
    }

    public java.util.List<T> getAll(final TYPE type) {
        return this.stream().filter(t -> getTypeResolver().isSameType(type, getTypeResolver().getTypeOf(t))).collect(Collectors.toList());

    }

    public void apply(TYPE type, java.util.function.Function<T, ?> functor) {
        this.getAll(type).stream().map(functor::apply).collect(Collectors.toList());
    }

}
