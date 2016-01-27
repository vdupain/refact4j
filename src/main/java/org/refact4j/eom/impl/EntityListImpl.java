package org.refact4j.eom.impl;

import org.refact4j.collection.CollectionHelper;
import org.refact4j.collection.impl.AbstractListImpl;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;
import org.refact4j.eom.EntityCollection;
import org.refact4j.eom.EntityList;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Key;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class EntityListImpl extends AbstractListImpl<EntityObject, Key, EntityDescriptor> implements EntityList {

    private static final long serialVersionUID = -1382509656778184779L;

    public EntityListImpl() {
        super();
    }

    public EntityListImpl(Collection<EntityObject> entityObjects) {
        this();
        this.addAll(entityObjects);
    }

    @Override
    public EntityObject findByIdentifier(Key key) {
        return super.findByIdentifier(key.getEntityDescriptor(), key);
    }

    public List<Key> getKeys() {
        return getKeys(this);
    }

    public static List<Key> getKeys(EntityCollection entityObjects) {
        return (List<Key>) CollectionHelper.transform(entityObjects, new Function<EntityObject, Key>() {

            public Key apply(EntityObject arg) {
                return arg.getKey();
            }
        });
    }

    public IdResolver<EntityObject, Key> getIdResolver() {
        return new EntityIdResolverImpl();
    }

    public TypeResolver<EntityObject, EntityDescriptor> getTypeResolver() {
        return new EntityTypeResolverImpl();
    }

}
