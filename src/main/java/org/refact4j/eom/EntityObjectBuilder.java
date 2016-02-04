package org.refact4j.eom;

import org.refact4j.eom.impl.EntityObjectImpl;
import org.refact4j.eom.model.*;

import java.util.Collection;
import java.util.Date;
import java.util.function.Supplier;

public final class EntityObjectBuilder implements Supplier<EntityObject>{

    private EntityObjectImpl entityObject;

    private EntityObjectBuilder(EntityDescriptor entityDescriptor) {
        this.entityObject = new EntityObjectImpl(entityDescriptor);
    }

    public static EntityObjectBuilder init(EntityDescriptor entityDescriptor) {
        return new EntityObjectBuilder(entityDescriptor);
    }

    public static EntityObjectBuilder init(EntityObject entityObject) {
        return new EntityObjectBuilder(entityObject.getEntityDescriptor());
    }

    public static EntityObjectBuilder initWithDefaultValues(EntityDescriptor entityDescriptor) {
        EntityObjectBuilder builder = new EntityObjectBuilder(entityDescriptor);
        EntityUtils.applyDefaultValues(builder.entityObject);
        return builder;
    }

    public EntityObjectBuilder set(StringField field, String value) {
        this.entityObject.set(field, value);
        return this;
    }

    public EntityObjectBuilder set(DoubleField field, Double value) {
        this.entityObject.set(field, value);
        return this;
    }

    public EntityObjectBuilder set(IntegerField field, Integer value) {
        this.entityObject.set(field, value);
        return this;
    }

    public EntityObjectBuilder set(DateField field, Date value) {
        this.entityObject.set(field, value);
        return this;
    }

    public EntityObjectBuilder set(BooleanField field, Boolean value) {
        this.entityObject.set(field, value);
        return this;
    }

    public EntityObjectBuilder set(ManyToOneRelationField field, EntityObject entityObject) {
        this.entityObject.set(field, entityObject != null ? entityObject.getKey() : null);
        return this;
    }

    public EntityObjectBuilder set(OneToOneRelationField field, EntityObject entityObject) {
        this.entityObject.set(field, entityObject != null ? entityObject.getKey() : null);
        return this;
    }

    public EntityObjectBuilder set(ManyToOneRelationField field, Key key) {
        this.entityObject.set(field, key);
        return this;
    }

    public EntityObjectBuilder set(OneToOneRelationField field, Key key) {
        this.entityObject.set(field, key);
        return this;
    }

    public EntityObjectBuilder set(OneToManyRelationType field, Collection<EntityObject> values) {
        throw new UnsupportedOperationException();
    }

    public EntityObjectBuilder set(Field field, Object value) {
        this.entityObject.set(field, value);
        return this;
    }

    public EntityObjectBuilder set(String fieldName, Object value) {
        Field field = this.entityObject.getEntityDescriptor().getField(fieldName);
        this.set(field, value);
        return this;
    }

    public EntityObject get() {
        return this.entityObject;
    }

}
