package org.refact4j.eom.impl;

import org.refact4j.collection.Set;
import org.refact4j.eom.*;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.StringField;
import org.refact4j.util.EqualsHelper;
import org.refact4j.util.NotImplementedException;
import org.refact4j.xml.DatasetHolder;

import java.util.List;
import java.util.stream.Collectors;

public class EntityDataSet extends EntitySetImpl implements DatasetHolder {

    private static final long serialVersionUID = 7143433867762051132L;

    public EntityDataSet() {
    }

    public EntityDataSet(EntityCollection entityObjects) {
        super();
        this.addAll(entityObjects);
    }

    public void apply(EntityDescriptor entityDescriptor, EntityFunctor<?> functor) {
        throw new NotImplementedException();
    }

    public org.refact4j.eom.EntityList getEntities(EntityDescriptor entityDescriptor, Field field, Object value) {
        return EntitySetBuilder.init().addAll(this).getEntitySet().getEntities(entityDescriptor, field, value);
    }

    public Set<EntityObject, Key, EntityDescriptor> getDataSet() {
        return this;
    }

    public EntityObject getEntityByName(EntityDescriptor entityDescriptor, final StringField stringField,
                                        final String value) {
        return  getAll(entityDescriptor).stream()
                .filter(arg -> EqualsHelper.equals(arg.get(stringField), value))
                .findFirst().orElse(null);
    }

    public EntityObject getEntityByPredicate(EntityDescriptor entityDescriptor, EntityPredicate entityObjectPredicate) {
        return  getAll(entityDescriptor).stream()
                .filter(entityObjectPredicate::apply)
                .findFirst().orElse(null);
    }

}
