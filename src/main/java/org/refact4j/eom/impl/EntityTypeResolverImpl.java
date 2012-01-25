package org.refact4j.eom.impl;

import org.refact4j.core.TypeResolver;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.EntityDescriptor;

public final class EntityTypeResolverImpl implements TypeResolver<EntityObject, EntityDescriptor> {

    public EntityDescriptor getTypeOf(EntityObject t) {
        return t.getEntityDescriptor();
    }

    public boolean isSameType(EntityDescriptor t1, EntityDescriptor t2) {
        return t1.equals(t2);
    }

}
