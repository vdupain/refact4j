package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.*;
import org.refact4j.expr.Expression;

@SuppressWarnings("serial")
public class StringFieldImpl extends AbstractField implements StringField {

    private Integer maxLength;
    private Integer minLength;

    public StringFieldImpl() {
    }

    public StringFieldImpl(String fieldName, String prettyName, EntityDescriptor entityDescriptor, String defaultValue,
                           boolean nullable, boolean visible, boolean editable, Integer order, Property property,
                           Expression<String> constraint) {
        super(fieldName, prettyName, entityDescriptor, defaultValue, nullable, visible, editable, order, property,
                constraint);
    }

    public void accept(DataTypeVisitor dataTypeVisitor) {
        dataTypeVisitor.visitStringType(this);
    }

    public void accept(FieldVisitor fieldVisitor) {
        fieldVisitor.visitStringField(this);
    }

    public Integer getMaxLength() {
        return this.maxLength;
    }

    public Integer getMinLength() {
        return this.minLength;
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

    @Override
    public void checkValue(Object value) {
        super.checkValue(value);
        Expression<Object> constraint = null;
        if (this.minLength != null) {
            constraint = new Expression<Object>(this.getName()).minLength(minLength);
        }
        if (this.maxLength != null) {
            Expression<Object> maxValueExpr = new Expression<Object>(this.getName()).maxLength(maxLength);
            if (constraint == null) {
                constraint = maxValueExpr;
            } else {
                constraint = constraint.and(maxValueExpr);
            }
        }
        this.checkConstraint(constraint, value);
    }

}
