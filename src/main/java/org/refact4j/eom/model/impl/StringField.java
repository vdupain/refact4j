package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.DataTypeVisitor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.FieldVisitor;

@SuppressWarnings("serial")
public class StringField extends AbstractField implements org.refact4j.eom.model.StringField {

    private Integer maxLength;
    private Integer minLength;

    public StringField() {
    }

    public StringField(String fieldName, String prettyName, EntityDescriptor entityDescriptor, String defaultValue,
                       boolean nullable, boolean visible, boolean editable, Integer order) {
        super(fieldName, prettyName, entityDescriptor, defaultValue, nullable, visible, editable, order
        );
    }

    public void accept(DataTypeVisitor dataTypeVisitor) {
        dataTypeVisitor.visitStringType(this);
    }

    public void accept(FieldVisitor fieldVisitor) {
        fieldVisitor.visitStringField(this);
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    protected Class<String> getDataTypeClass() {
        return String.class;
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.DATA_TYPE, DataTypeType.STRING_DATA_TYPE).set(FieldDesc.MIN_LENGTH, minLength).set(
                FieldDesc.MAX_LENGTH, maxLength);
        return entity;
    }

}
