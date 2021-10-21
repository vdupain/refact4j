package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.DataTypeVisitor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.FieldVisitor;

@SuppressWarnings("serial")
public class DoubleField extends AbstractNumberField implements org.refact4j.eom.model.DoubleField {

    public DoubleField() {
    }

    public DoubleField(String fieldName, String prettyName, EntityDescriptor entityDescriptor, Double defaultValue,
                       boolean nullable, Integer order) {
        super(fieldName, prettyName, entityDescriptor, defaultValue, nullable
        );
    }

    public void accept(DataTypeVisitor dataTypeVisitor) {
        dataTypeVisitor.visitDoubleType(this);
    }

    public void accept(FieldVisitor fieldVisitor) {
        fieldVisitor.visitDoubleField(this);
    }

    protected Class<Double> getDataTypeClass() {
        return Double.class;
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.DATA_TYPE, DataTypeType.DOUBLE_DATA_TYPE);
        return entity;
    }
}
