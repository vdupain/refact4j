package org.refact4j.collection;

import java.util.Collection;
import java.util.Iterator;

public class CollectionDecorator<T> implements Collection<T> {

    private final Collection<T> collection;
    private final ChangeSetImpl<T> changeSet;
    private final ChangeSetSupport<T> changeSetSupport = new ChangeSetSupport<>();


    public CollectionDecorator(Collection<T> collection) {
        this.collection = collection;
        this.changeSet = new ChangeSetImpl<>(changeSetSupport);
    }

    public Collection<T> getCollection() {
        return this.collection;
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

    public <T1> T1[] toArray(T1[] a) {
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
