package org.refact4j.eom.impl;

import org.refact4j.collection.ChangeSetDelta;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.Field;

public class EntityObjectChangeSetDelta implements ChangeSetDelta<EntityObject> {
    private Field field;
    private Object oldValue;
    private Object newValue;
    private EntityObject source;

    public EntityObjectChangeSetDelta(EntityObject source, Field field, Object oldValue, Object newValue) {
        this.source = source;
        this.field = field;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public EntityObject getSource() {
        return this.source;
    }

    public String getProperty() {
        return this.field.getName();
    }

    public Object getOldValue() {
        return this.oldValue;
    }

    public Object getNewValue() {
        return this.newValue;
    }

    public Field getField() {
        return this.field;
    }
}
