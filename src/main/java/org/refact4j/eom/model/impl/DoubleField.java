package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.DataTypeVisitor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.FieldVisitor;
import org.refact4j.expr.Expression;

@SuppressWarnings("serial")
public class DoubleField extends AbstractNumberField implements org.refact4j.eom.model.DoubleField {

    public DoubleField() {
    }

    public DoubleField(String fieldName, String prettyName, EntityDescriptor entityDescriptor, Double defaultValue,
                       boolean nullable, boolean visible, boolean editable, Integer order,
                       Expression<Double> constraint) {
        super(fieldName, prettyName, entityDescriptor, defaultValue, nullable, visible, editable, order,
                constraint);
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
