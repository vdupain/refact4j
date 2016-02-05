package org.refact4j.eom.impl;

import org.refact4j.collection.Set;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntitySet;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Key;

import java.util.Collection;

public class EntitySetImpl extends Set<EntityObject, Key, EntityDescriptor> implements EntitySet {


    public EntitySetImpl() {
        super();
    }

    public EntitySetImpl(Collection<EntityObject> entityObjects) {
        this();
        this.addAll(entityObjects);
    }

    public EntityObject findByIdentifier(Key key) {
        return this.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst().get();
    }


    public IdResolver<EntityObject, Key> getIdResolver() {
        return new EntityIdResolver();
    }

    public TypeResolver<EntityObject, EntityDescriptor> getTypeResolver() {

        return new EntityTypeResolverImpl();
    }

}
