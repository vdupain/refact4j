package org.refact4j.collection.impl;

import org.refact4j.collection.ChangeSet;
import org.refact4j.functor.UnaryPredicate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractListImpl<T, ID extends Serializable, TYPE> extends ArrayList<T> implements
        org.refact4j.collection.List<T, ID, TYPE> {

    private final CollectionDecorator<T, ID, TYPE> collectionDecorator = new CollectionDecorator<T, ID, TYPE>(this);

    protected AbstractListImpl() {
        this.collectionDecorator.setTypeResolver(getTypeResolver());
        this.collectionDecorator.setIdResolver(getIdResolver());
    }

    public T findByIdentifier(ID id) {
        return collectionDecorator.findByIdentifier(id);
    }

    public T findByIdentifier(TYPE type, ID id) {
        return collectionDecorator.findByIdentifier(type, id);
    }

    public List<T> findByPredicate(UnaryPredicate<T> predicate) {
        return collectionDecorator.findByPredicate(predicate);
    }

    public T findUnique(TYPE type, UnaryPredicate<T> predicate) {
        return collectionDecorator.findUnique(type, predicate);
    }

    public List<T> getAll(final TYPE type) {
        return collectionDecorator.getAll(type);
    }

    public List<T> getAll(TYPE type, UnaryPredicate<T> predicate) {
        return collectionDecorator.getAll(type, predicate);
    }

    public void apply(TYPE type, java.util.function.Function<T,?> functor) {
        this.getAll(type).stream().map(e -> functor.apply(e)).collect(Collectors.toList());
    }


    public ChangeSet<T> getChangeSet() {
        return collectionDecorator.getChangeSet();
    }

    public void delete(T t) {
        collectionDecorator.delete(t);
    }

    public void create(T t) {
        collectionDecorator.create(t);
    }

}
