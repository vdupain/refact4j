package org.refact4j.collection;

import org.refact4j.core.Finder;
import org.refact4j.functor.UnaryPredicate;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class List<T, ID, TYPE> extends ArrayList<T> implements
        Finder<T, ID, TYPE> {

    private final CollectionDecorator<T, ID, TYPE> collectionDecorator = new CollectionDecorator<T, ID, TYPE>(this);

    public List() {
        this.collectionDecorator.setTypeResolver(getTypeResolver());
        this.collectionDecorator.setIdResolver(getIdResolver());
    }

    public T findByIdentifier(ID id) {
        return collectionDecorator.findByIdentifier(id);
    }

    public T findByIdentifier(TYPE type, ID id) {
        return collectionDecorator.findByIdentifier(type, id);
    }

    public java.util.List<T> findByPredicate(UnaryPredicate<T> predicate) {
        return collectionDecorator.findByPredicate(predicate);
    }

    public T findUnique(TYPE type, UnaryPredicate<T> predicate) {
        return collectionDecorator.findUnique(type, predicate);
    }

    public java.util.List<T> getAll(final TYPE type) {
        return collectionDecorator.getAll(type);
    }

    public java.util.List<T> getAll(TYPE type, UnaryPredicate<T> predicate) {
        return collectionDecorator.getAll(type, predicate);
    }

    public void apply(TYPE type, java.util.function.Function<T, ?> functor) {
        this.getAll(type).stream().map(e -> functor.apply(e)).collect(Collectors.toList());
    }

}
