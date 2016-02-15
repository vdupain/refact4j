package org.refact4j.eom.impl;

import org.refact4j.collection.Set;
import org.refact4j.core.TypeResolver;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Key;

import java.util.Collection;

public class EntitySet extends Set<EntityObject, Key, EntityDescriptor> {


    public EntitySet() {
        super();
    }

    public EntitySet(Collection<EntityObject> entityObjects) {
        this();
        this.addAll(entityObjects);
    }


    public TypeResolver<EntityObject, EntityDescriptor> getTypeResolver() {

        return new EntityTypeResolverImpl();
    }

}
