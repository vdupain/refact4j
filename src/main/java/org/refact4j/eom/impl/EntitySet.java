package org.refact4j.eom.impl;

import org.refact4j.collection.Set;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Key;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySet extends Set<EntityObject> {


    public EntitySet() {
        super();
    }

    public EntitySet(Collection<EntityObject> entityObjects) {
        this();
        this.addAll(entityObjects);
    }

    public List<EntityObject> mgetAll(final EntityDescriptor entityDescriptor) {
        return this.stream()
                .filter(p -> p.getEntityDescriptor().equals(entityDescriptor))
                .collect(Collectors.toList());
    }

}
