package org.refact4j.eom.impl;

import org.refact4j.collection.impl.AbstractSetImpl;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;
import org.refact4j.eom.EntityList;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntitySet;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.util.EqualsHelper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        List<EntityObject> collect = this.stream().filter(e -> EqualsHelper.equals(e.get(field), value)).collect(Collectors.toList());
        return new EntityListImpl(collect);
    }

    @Override
    public EntityObject findByIdentifier(Key key) {
        return super.findByIdentifier(key.getEntityDescriptor(), key);
    }

    public IdResolver<EntityObject, Key> getIdResolver() {
        return new EntityIdResolverImpl();
    }

    public TypeResolver<EntityObject, EntityDescriptor> getTypeResolver() {
        return new EntityTypeResolverImpl();
    }

}
