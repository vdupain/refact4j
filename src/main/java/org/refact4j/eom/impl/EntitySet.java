package org.refact4j.eom.impl;

import org.refact4j.collection.Set;
import org.refact4j.eom.EntityObject;

import java.util.Collection;

public class EntitySet extends Set<EntityObject> {


    public EntitySet() {
        super();
    }

    public EntitySet(Collection<EntityObject> entityObjects) {
        this();
        this.addAll(entityObjects);
    }

}
