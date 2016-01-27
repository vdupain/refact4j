package org.refact4j.collection.impl;

import org.refact4j.collection.ChangeSet;
import org.refact4j.collection.CollectionHelper;
import org.refact4j.collection.Set;
import org.refact4j.functor.UnaryPredicate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

public abstract class AbstractSetImpl<T, ID extends Serializable, TYPE> extends HashSet<T> implements Set<T, ID, TYPE> {

    private final CollectionDecorator<T, ID, TYPE> collectionDecorator = new CollectionDecorator<T, ID, TYPE>(this);


    protected AbstractSetImpl() {
        collectionDecorator.setIdResolver(getIdResolver());
        collectionDecorator.setTypeResolver(getTypeResolver());
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
        CollectionHelper.foreach(this.getAll(type).iterator(), functor);
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
