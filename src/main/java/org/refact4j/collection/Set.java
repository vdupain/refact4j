package org.refact4j.collection;

import org.refact4j.core.Finder;
import org.refact4j.functor.UnaryPredicate;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Set<T, ID, TYPE> extends HashSet<T> implements Finder<T, ID, TYPE> {

    private final CollectionDecorator<T, ID, TYPE> collectionDecorator = new CollectionDecorator<T, ID, TYPE>(this);


    public Set() {
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

    public void apply(TYPE type, java.util.function.Function<T, ?> functor) {
        this.getAll(type).stream().map(e -> functor.apply(e)).collect(Collectors.toList());
    }

}
