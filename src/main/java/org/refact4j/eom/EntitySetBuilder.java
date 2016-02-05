package org.refact4j.eom;

import org.refact4j.eom.impl.EntitySet;

import java.util.Collection;

/**
 * EntitySetBuilder is a class that is used to create EntitySet or read only
 * EntitySet from EntityObject or collection of EntityObjects.
 */
public final class EntitySetBuilder {

    private final EntitySet entityObjectSet;

    private EntitySetBuilder() {
        this.entityObjectSet = new EntitySet();
    }

    public static EntitySetBuilder init() {
        return new EntitySetBuilder();
    }

    public EntitySetBuilder addAll(Collection<EntityObject> entityObjects) {
        entityObjectSet.addAll(entityObjects);
        return this;
    }

    public EntitySetBuilder add(EntityObject entityObject) {
        entityObjectSet.add(entityObject);
        return this;
    }

    public EntitySet get() {
        return this.entityObjectSet;
    }

}
