package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.*;
import org.refact4j.expr.Expression;

@SuppressWarnings("serial")
public class BooleanFieldImpl extends AbstractField implements BooleanField {

    public BooleanFieldImpl() {
    }

    public BooleanFieldImpl(String fieldName, String prettyName, EntityDescriptor entityDescriptor,
                            Boolean defaultValue, boolean nullable, boolean visible, boolean editable, Integer order,
                            Property property, Expression<Boolean> constraint) {
        super(fieldName, prettyName, entityDescriptor, defaultValue, nullable, visible, editable, order, property,
                constraint);
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
