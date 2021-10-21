package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.DataTypeVisitor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.FieldVisitor;

@SuppressWarnings("serial")
public class BooleanField extends AbstractField implements org.refact4j.eom.model.BooleanField {

    public BooleanField() {
    }

    public BooleanField(String fieldName, String prettyName, EntityDescriptor entityDescriptor,
                        Boolean defaultValue, boolean nullable) {
        super(fieldName, prettyName, entityDescriptor, defaultValue, nullable
        );
    }

    public void accept(DataTypeVisitor dataTypeVisitor) {
        dataTypeVisitor.visitBooleanType(this);
    }

    public void accept(FieldVisitor fieldVisitor) {
        fieldVisitor.visitBooleanField(this);
    }

    protected Class<Boolean> getDataTypeClass() {
        return Boolean.class;
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.DATA_TYPE, DataTypeType.BOOLEAN_DATA_TYPE);
        return entity;
    }

}
