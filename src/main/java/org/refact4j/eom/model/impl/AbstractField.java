package org.refact4j.eom.model.impl;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.*;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class AbstractField implements Field {

    private String fieldName;

    private EntityDescriptor entityDescriptor;

    private Object defaultValue;

    private boolean nullable = true;

    private String prettyName;

    AbstractField() {
    }

    AbstractField(String fieldName, String prettyName, EntityDescriptor entityDescriptor, Object defaultValue,
                  boolean nullable) {
        this.fieldName = fieldName;
        this.prettyName = prettyName;
        this.entityDescriptor = entityDescriptor;
        this.defaultValue = defaultValue;
        this.nullable = nullable;
    }

    public String getName() {
        return this.fieldName;
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

    public boolean isNullable() {
        return this.nullable;
    }

    public String toString() {
        return Stringifiers.FIELD_PRETTY.stringify(this);
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
                .set(FieldDesc.ENTITY_DESC, this.getEntityDescriptor().toEntity())
                .set(FieldDesc.NAME, this.fieldName)
                .set(FieldDesc.PRETTY_NAME, this.prettyName)
                .set(FieldDesc.IS_KEY, this.entityDescriptor.getKeyFields().contains(this))
                .set(FieldDesc.NULLABLE, this.nullable)
                .set(FieldDesc.DEFAULT_VALUE, ConverterHelper.convertValue2String(defaultValue, this))
                .get();
    }

    public boolean isKey() {
        return this.entityDescriptor.getKeyFields().contains(this);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.fieldName = (String) in.readObject();
        this.prettyName = (String) in.readObject();
        this.defaultValue = in.readObject();
        this.entityDescriptor = (EntityDescriptor) in.readObject();
        this.nullable = in.readBoolean();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.fieldName);
        out.writeObject(this.prettyName);
        out.writeObject(this.defaultValue);
        out.writeObject(this.entityDescriptor);
        out.writeBoolean(this.nullable);
    }
}
