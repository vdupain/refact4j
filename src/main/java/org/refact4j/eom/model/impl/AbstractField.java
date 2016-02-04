package org.refact4j.eom.model.impl;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.*;
import org.refact4j.expr.Expression;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class AbstractField implements Field {

    private String fieldName;

    private EntityDescriptor entityDescriptor;

    private Object defaultValue;

    private boolean nullable = true;

    private boolean visible;

    private boolean editable;

    private String prettyName;

    private Integer order;

    private Property property;

    private Expression<?> constraint;

    AbstractField() {
    }

    AbstractField(String fieldName, String prettyName, EntityDescriptor entityDescriptor, Object defaultValue,
                  boolean nullable, boolean visible, boolean editable, Integer order, Property property,
                  Expression<?> constraint) {
        this.fieldName = fieldName;
        this.prettyName = prettyName;
        this.entityDescriptor = entityDescriptor;
        this.defaultValue = defaultValue;
        this.nullable = nullable;
        this.visible = visible;
        this.editable = editable;
        this.order = order;
        this.property = property;
        this.constraint = constraint;
    }

    public String getName() {
        return this.fieldName;
    }

    public String getFullName() {
        return getEntityDescriptor().getName() + "." + getName();
    }

    public String getPrettyName() {
        return this.prettyName;
    }

    public DataType getDataType() {
        return this;
    }

    public EntityDescriptor getEntityDescriptor() {
        return entityDescriptor;
    }

    public Object getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void checkValue(Object value) {
        if (value == null && !nullable) {
            throw new NullFieldValueNotAllowedException(this);
        }
        checkClassValue(value);
        checkConstraint(constraint, value);
    }

    protected abstract Class<?> getDataTypeClass();

    private void checkClassValue(Object value) {
        if (value == null) {
            return;
        }
        if (this.getDataTypeClass() != null && !this.getDataTypeClass().isInstance(value)) {
            throw new InvalidFieldValueException(this, "Value must be instance of " + this.getDataTypeClass().getName()
                    + " but was " + value.getClass().getName());
        }
    }

    void checkConstraint(Expression constraint, Object value) {
        if (constraint == null) {
            return;
        }
        if (!constraint.test(value)) {
            throw new InvalidFieldValueException(this, constraint.toString());
        }
    }

    public Integer getOrder() {
        return this.order;
    }

    public boolean isNullable() {
        return this.nullable;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean isEditable() {
        return this.editable;
    }

    public String toString() {
        return Stringifiers.PRETTY.stringify(this);
    }

    public void addProperty(Object key, Object value) {
        this.property.addProperty(key, value);
    }

    public Object getProperty(Object key) {
        return this.property.getProperty(key);
    }

    public Property getProperty() {
        return this.property;
    }

    public String toXmlString() {
        return Stringifiers.XML.stringify(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
        result = prime * result + ((entityDescriptor == null) ? 0 : entityDescriptor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Field other = (Field) obj;
        return this.getName().equals(other.getName()) && this.getEntityDescriptor().equals(other.getEntityDescriptor());
    }

    public EntityObject toEntity() {
        return EntityObjectBuilder.init(FieldDesc.INSTANCE)
                .set(FieldDesc.ENTITY_DESC, this.entityDescriptor.toEntity())
                .set(FieldDesc.NAME, this.fieldName)
                .set(FieldDesc.PRETTY_NAME, this.prettyName)
                .set(FieldDesc.IS_KEY, this.entityDescriptor.getKeyFields().contains(this))
                .set(FieldDesc.NULLABLE, this.nullable)
                .set(FieldDesc.VISIBLE, this.visible)
                .set(FieldDesc.EDITABLE, this.editable)
                .set(FieldDesc.ORDER, this.order)
                .set(FieldDesc.DEFAULT_VALUE, ConverterHelper.convertValue2String(defaultValue, this))
                .getEntity();
    }

    public boolean isKey() {
        return this.entityDescriptor.getKeyFields().contains(this);
    }

    public Expression<?> getConstraintExpression() {
        return this.constraint;
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.fieldName = (String) in.readObject();
        this.prettyName = (String) in.readObject();
        this.defaultValue = in.readObject();
        this.entityDescriptor = (EntityDescriptor) in.readObject();
        this.order = (Integer) in.readObject();
        this.editable = in.readBoolean();
        this.visible = in.readBoolean();
        this.nullable = in.readBoolean();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.fieldName);
        out.writeObject(this.prettyName);
        out.writeObject(this.defaultValue);
        out.writeObject(this.entityDescriptor);
        out.writeObject(this.order);
        out.writeBoolean(this.editable);
        out.writeBoolean(this.visible);
        out.writeBoolean(this.nullable);
    }
}
