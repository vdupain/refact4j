package org.refact4j.eom;

import org.refact4j.eom.impl.EntityObjectImpl;
import org.refact4j.eom.model.*;

import java.util.Date;

public final class EntityObjectBuilder {

    private EntityObjectImpl entityObject;

    private EntityObjectBuilder() {
    }

    private EntityObjectBuilder(EntityDescriptor entityDescriptor) {
        this.entityObject = new EntityObjectImpl(entityDescriptor);
    }

    private EntityObjectBuilder(EntityDescriptor type, Key entityObjectKey) {
        this(type);
        for (Field field : type.getKeyFields()) {
            entityObject.set(field, entityObjectKey.getFieldValue(field));
        }
    }

    private EntityObjectBuilder(EntityObjectImpl entityObject) {
        this.entityObject = (EntityObjectImpl) entityObject.clone();
    }

    public static EntityObjectBuilder init(EntityDescriptor entityDescriptor) {
        return new EntityObjectBuilder(entityDescriptor);
    }

    public static EntityObjectBuilder init(EntityObject entityObject) {
        EntityObjectBuilder builder = new EntityObjectBuilder(entityObject.getEntityDescriptor(), entityObject.getKey());
        for (Field field : entityObject.getEntityDescriptor().getFields()) {
            builder.set(field, entityObject.get(field));
        }
        return builder;
    }

    public static EntityObjectBuilder initWithDefaultValues(EntityDescriptor entityDescriptor) {
        EntityObjectBuilder builder = new EntityObjectBuilder(entityDescriptor);
        EntityUtils.applyDefaultValues(builder.entityObject);
        return builder;
    }

    public EntityObjectBuilder setFieldValue(Field field, Object value) {
        this.entityObject.set(field, value);
        return this;
    }

    public EntityObjectBuilder set(StringField field, String value) {
        setFieldValue(field, value);
        return this;
    }

    public EntityObjectBuilder set(DoubleField field, Double value) {
        setFieldValue(field, value);
        return this;
    }

    public EntityObjectBuilder set(IntegerField field, Integer value) {
        setFieldValue(field, value);
        return this;
    }

    public EntityObjectBuilder set(DateField field, Date value) {
        setFieldValue(field, value);
        return this;
    }

    public EntityObjectBuilder set(BooleanField field, Boolean value) {
        setFieldValue(field, value);
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

    public EntityObjectBuilder set(OneToManyRelationType field, EntityCollection values) {
        throw new UnsupportedOperationException();
    }

    public EntityObjectBuilder set(Field field, Object value) {
        this.setFieldValue(field, value);
        return this;
    }

    public EntityObjectBuilder set(String fieldName, Object value) {
        Field field = this.entityObject.getEntityDescriptor().getField(fieldName);
        this.set(field, value);
        return this;
    }

    public EntityObject getCheckEntity() {
        this.entityObject.checkConstraint();
        return this.getEntity();
    }

    public EntityObject getEntity() {
        return this.entityObject;
    }

}
