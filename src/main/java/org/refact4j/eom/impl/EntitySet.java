package org.refact4j.eom.impl;

import org.refact4j.eom.EntityObject;

import java.util.Collection;
import java.util.HashSet;

public class EntitySet extends HashSet<EntityObject> {


    public EntitySet() {

    }

    public EntitySet(Collection<EntityObject> entityObjects) {
        this();
        this.addAll(entityObjects);
    }

}
