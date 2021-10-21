package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.DataTypeVisitor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.FieldVisitor;
import org.refact4j.eom.model.IntegerField;

@SuppressWarnings("serial")
public class IntegerFieldImpl extends AbstractNumberField implements IntegerField {

    public IntegerFieldImpl() {

    }

    public IntegerFieldImpl(String fieldName, String prettyName, EntityDescriptor entityDescriptor,
                            Integer defaultValue, boolean nullable, Integer order) {
        super(fieldName, prettyName, entityDescriptor, defaultValue, nullable
        );
    }

    public void accept(DataTypeVisitor dataTypeVisitor) {
        dataTypeVisitor.visitIntegerType(this);
    }

    public void accept(FieldVisitor fieldVisitor) {
        fieldVisitor.visitIntegerField(this);
    }

    protected Class<Integer> getDataTypeClass() {
        return Integer.class;
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.DATA_TYPE, DataTypeType.INTEGER_DATA_TYPE);
        return entity;
    }

}
