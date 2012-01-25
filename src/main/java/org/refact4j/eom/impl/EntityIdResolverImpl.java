package org.refact4j.eom.impl;

import org.refact4j.core.IdResolver;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.Key;

public final class EntityIdResolverImpl implements IdResolver<EntityObject, Key> {

    public Key getId(EntityObject t) {
        return t.getKey();
    }

}
