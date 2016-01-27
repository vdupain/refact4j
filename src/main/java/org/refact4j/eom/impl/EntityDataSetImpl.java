package org.refact4j.eom.impl;

import org.refact4j.collection.DataSet;
import org.refact4j.eom.EntityCollection;
import org.refact4j.eom.EntityFunctor;
import org.refact4j.eom.EntityList;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityPredicate;
import org.refact4j.eom.EntitySet;
import org.refact4j.eom.EntitySetBuilder;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.StringField;
import org.refact4j.util.EqualsHelper;
import org.refact4j.util.NotImplementedException;
import org.refact4j.xml.DatasetHolder;

import java.util.List;

public class EntityDataSetImpl extends EntityListImpl implements EntitySet,
        DataSet<EntityObject, Key, EntityDescriptor>, DatasetHolder {

    private static final long serialVersionUID = 7143433867762051132L;

    public EntityDataSetImpl() {
    }

    public EntityDataSetImpl(EntityCollection entityObjects) {
        super();
        this.addAll(entityObjects);
    }

    public void apply(EntityDescriptor entityDescriptor, EntityFunctor<?> functor) {
        throw new NotImplementedException();
    }

    public EntityList getEntities(EntityDescriptor entityDescriptor, Field field, Object value) {
        return EntitySetBuilder.init().addAll(this).getEntitySet().getEntities(entityDescriptor, field, value);
    }

    public DataSet<EntityObject, Key, EntityDescriptor> getDataSet() {
        return this;
    }

    public EntityObject getEntityByName(EntityDescriptor entityDescriptor, final StringField stringField,
                                        final String value) {
        List<EntityObject> list = getAll(entityDescriptor, new EntityPredicate() {
            public Boolean apply(EntityObject arg) {
                return EqualsHelper.equals(arg.get(stringField), value);
            }
        });
        return list.size() > 0 ? list.get(0) : null;
    }

    public EntityObject getEntityByPredicate(EntityDescriptor entityDescriptor, EntityPredicate entityObjectPredicate) {
        List<EntityObject> list = getAll(entityDescriptor, entityObjectPredicate);
        return list.size() > 0 ? list.get(0) : null;
    }

}
