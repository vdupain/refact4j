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

    private static final long serialVersionUID = 5875020382043001900L;

    public EntitySetImpl() {
        super();
    }

    public EntitySetImpl(Collection<EntityObject> entityObjects) {
        this();
        this.addAll(entityObjects);
    }


    @Override
    public EntityObject findByIdentifier(Key key) {
        return super.findByIdentifier(key.getEntityDescriptor(), key);
    }

    public IdResolver<EntityObject, Key> getIdResolver() {
        return new EntityIdResolver();
    }

    public TypeResolver<EntityObject, EntityDescriptor> getTypeResolver() {

        return new EntityTypeResolverImpl();
    }

}
