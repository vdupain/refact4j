package org.refact4j.eom.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.Key;

import java.util.ArrayList;
import java.util.Collection;

public class EntityList extends ArrayList<EntityObject> {


    public EntityList() {
        super();
    }

    public EntityList(Collection<EntityObject> entityObjects) {
        this();
        this.addAll(entityObjects);
    }


    public EntityObject findByIdentifier(Key key) {
        return this.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst().get();
    }


}
