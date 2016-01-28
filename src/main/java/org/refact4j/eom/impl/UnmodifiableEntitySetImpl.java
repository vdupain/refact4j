package org.refact4j.eom.impl;

import org.refact4j.collection.ChangeSet;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;
import org.refact4j.eom.EntityList;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntitySet;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.functor.UnaryPredicate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class UnmodifiableEntitySetImpl implements EntitySet {

    private final EntitySet entityObjectSet;

    public UnmodifiableEntitySetImpl(EntitySet entityObjectSet) {
        this.entityObjectSet = entityObjectSet;
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Iterator<EntityObject> iterator() {
        return new Iterator<EntityObject>() {
            private final Iterator<? extends EntityObject> i = entityObjectSet.iterator();

            public boolean hasNext() {
                return i.hasNext();
            }

            public EntityObject next() {
                return i.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public boolean add(EntityObject o) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends EntityObject> c) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(EntityObject entityObject) {
        return entityObjectSet.contains(entityObject);
    }

    public EntityObject findUnique(EntityDescriptor entityDescriptor, UnaryPredicate<EntityObject> entityObjectPredicate) {
        return entityObjectSet.findUnique(entityDescriptor, entityObjectPredicate);
    }

    public List<EntityObject> getAll(EntityDescriptor entityDescriptor) {
        return entityObjectSet.getAll(entityDescriptor);
    }

    public List<EntityObject> getAll(EntityDescriptor entityDescriptor, UnaryPredicate<EntityObject> unaryPredicate) {
        return entityObjectSet.getAll(entityDescriptor, unaryPredicate);
    }

    public List<EntityObject> findByPredicate(UnaryPredicate<EntityObject> entityObjectPredicate) {
        return entityObjectSet.findByPredicate(entityObjectPredicate);
    }

    public EntityList getEntities(EntityDescriptor entityDescriptor, Field field, Object value) {
        return entityObjectSet.getEntities(entityDescriptor, field, value);
    }

    public void delete(EntityObject entityObject) {
        this.entityObjectSet.delete(entityObject);
    }

    public void create(EntityObject entityObject) {
        this.entityObjectSet.add(entityObject);
    }

    public boolean contains(Object o) {
        return entityObjectSet.contains(o);
    }

    public boolean containsAll(Collection<?> c) {
        return entityObjectSet.containsAll(c);
    }

    public boolean isEmpty() {
        return entityObjectSet.isEmpty();
    }

    public int size() {
        return entityObjectSet.size();
    }

    public Object[] toArray() {
        return entityObjectSet.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return entityObjectSet.toArray(a);
    }

    public String toString() {
        return entityObjectSet.toString();
    }

    public EntityObject findByIdentifier(Key id) {
        return this.entityObjectSet.findByIdentifier(id);
    }

    public EntityObject findByIdentifier(EntityDescriptor entityDescriptor, Key id) {
        return this.entityObjectSet.findByIdentifier(entityDescriptor, id);
    }

    public List<Key> getKeys() {
        return this.entityObjectSet.getKeys();
    }

    public IdResolver<EntityObject, Key> getIdResolver() {
        return this.entityObjectSet.getIdResolver();
    }

    public TypeResolver<EntityObject, EntityDescriptor> getTypeResolver() {
        return this.entityObjectSet.getTypeResolver();
    }

}
