package org.refact4j.collection.impl;

import org.refact4j.collection.ChangeSet;
import org.refact4j.core.Finder;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;
import org.refact4j.functor.UnaryPredicate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionDecorator<T, ID extends Serializable, TYPE> implements Collection<T>, Finder<T, ID, TYPE> {

    private final Collection<T> collection;
    private TypeResolver<T, TYPE> typeResolver;
    private IdResolver<T, ID> idResolver;

    private ChangeSetImpl<T> changeSet;
    private ChangeSetSupport<T> changeSetSupport = new ChangeSetSupport<T>();


    public CollectionDecorator(Collection<T> collection) {
        this.collection = collection;
        this.changeSet = new ChangeSetImpl<T>(changeSetSupport);
    }

    public Collection<T> getCollection() {
        return this.collection;
    }

    public T findByIdentifier(ID id) {
        for (T t : this.collection) {
            if (idResolver.getId(t).equals(id)) {
                return t;
            }
        }
        return null;
    }

    public T findByIdentifier(TYPE type, ID id) {
        for (T t : this.getAll(type)) {
            if (idResolver.getId(t).equals(id)) {
                return t;
            }
        }
        return null;
    }

    public List<T> findByPredicate(UnaryPredicate<T> predicate) {
        return this.collection.stream().filter(t -> predicate.apply(t)).collect(Collectors.toList());
    }

    public T findUnique(TYPE type, UnaryPredicate<T> predicate) {
        try {
            return getAll(type, predicate).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getAll(final TYPE type) {
        return this.collection.stream().filter(t -> typeResolver.isSameType(type, typeResolver.getTypeOf(t))).collect(Collectors.toList());
    }

    public List<T> getAll(TYPE type, UnaryPredicate<T> predicate) {
        return this.getAll(type).stream().filter(t -> predicate.apply(t)).collect(Collectors.toList());
    }

    public void setTypeResolver(TypeResolver<T, TYPE> typeResolver) {
        this.typeResolver = typeResolver;
    }

    public void setIdResolver(IdResolver<T, ID> idResolver) {
        this.idResolver = idResolver;
    }


    public int size() {
        return this.collection.size();
    }

    public boolean isEmpty() {
        return this.collection.isEmpty();
    }

    public boolean contains(Object o) {
        return this.collection.contains(o);
    }

    public Iterator<T> iterator() {
        return this.collection.iterator();
    }

    public Object[] toArray() {
        return this.collection.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return this.collection.toArray(a);
    }

    public boolean add(T o) {
        return this.collection.add(o);
    }

    public boolean remove(Object o) {
        return this.collection.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return this.collection.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return this.collection.addAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return this.collection.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return this.collection.retainAll(c);
    }

    public void clear() {
        this.collection.clear();
    }

    public void delete(T t) {
        this.remove(t);
        changeSetSupport.fireChangeSetDelete(t);
    }

    public void create(T t) {
        this.add(t);
        changeSetSupport.fireChangeSetCreate(t);
    }

    public ChangeSet<T> getChangeSet() {
        return this.changeSet;
    }
}
