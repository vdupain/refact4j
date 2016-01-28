package org.refact4j.eom.impl;

import org.refact4j.collection.impl.AbstractSetImpl;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;
import org.refact4j.eom.EntityFunctor;
import org.refact4j.eom.EntityList;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntitySet;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.util.EqualsHelper;

import java.util.Collection;
import java.util.List;

public class EntitySetImpl extends AbstractSetImpl<EntityObject, Key, EntityDescriptor> implements EntitySet {

    private static final long serialVersionUID = 5875020382043001900L;

    public EntitySetImpl() {
        super();
    }

    public EntitySetImpl(Collection<EntityObject> entityObjects) {
        this();
        this.addAll(entityObjects);
    }

    public EntityList getEntities(EntityDescriptor entityDescriptor, final Field field, final Object value) {
        final EntityList result = new EntityListImpl();
        apply(entityDescriptor, new EntityFunctor<Object>() {

            public Object apply(EntityObject entityObject) {
                if (EqualsHelper.equals(entityObject.get(field), value)) {
                    result.add(entityObject);
                }
                return null;
            }
        });
        return result;
    }

    @Override
    public EntityObject findByIdentifier(Key key) {
        return super.findByIdentifier(key.getEntityDescriptor(), key);
    }

    public List<Key> getKeys() {
        return EntityListImpl.getKeys(this);
    }

    public IdResolver<EntityObject, Key> getIdResolver() {
        return new EntityIdResolverImpl();
    }

    public TypeResolver<EntityObject, EntityDescriptor> getTypeResolver() {
        return new EntityTypeResolverImpl();
    }

}
