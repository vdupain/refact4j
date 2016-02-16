package org.refact4j.eom.impl;

import org.refact4j.collection.Set;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.StringField;
import org.refact4j.xml.DatasetHolder;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public class EntityDataSet extends EntitySet implements DatasetHolder {

    private static final long serialVersionUID = 7143433867762051132L;

    public EntityDataSet() {
    }

    public EntityDataSet(Collection<EntityObject> entityObjects) {
        super();
        this.addAll(entityObjects);
    }


    public Set<EntityObject> getDataSet() {
        return this;
    }

    public EntityObject getEntityByName(EntityDescriptor entityDescriptor, final StringField stringField,
                                        final String value) {
        return stream()
                .filter(arg -> arg.getEntityDescriptor().equals(entityDescriptor))
                .filter(arg -> Objects.equals(arg.get(stringField), value))
                .findFirst().orElse(null);
    }

    public EntityObject getEntityByPredicate(EntityDescriptor entityDescriptor, Predicate<EntityObject> entityObjectPredicate) {
        return stream()
                .filter(arg -> arg.getEntityDescriptor().equals(entityDescriptor))
                .filter(entityObjectPredicate)
                .findFirst().orElse(null);
    }

}
