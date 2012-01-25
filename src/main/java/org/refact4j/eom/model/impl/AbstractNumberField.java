package org.refact4j.eom.model.impl;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.NumericField;
import org.refact4j.eom.model.Property;
import org.refact4j.expr.Expression;

public abstract class AbstractNumberField extends AbstractField implements NumericField {

    private Number minValue;
    private Number maxValue;

    AbstractNumberField() {
    }

    AbstractNumberField(String fieldName, String prettyName, EntityDescriptor entityDesc, Number defaultValue,
                        boolean nullable, boolean visible, boolean editable, Integer order, Property property,
                        Expression<? extends Number> constraint) {
        super(fieldName, prettyName, entityDesc, defaultValue, nullable, visible, editable, order, property, constraint);
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.MIN_VALUE, ConverterHelper.convertValue2String(maxValue, this)).set(FieldDesc.MAX_VALUE,
                ConverterHelper.convertValue2String(minValue, this));
        return entity;
    }

    @Override
    public void checkValue(Object value) {
        super.checkValue(value);
        Expression<Object> minMaxValueExpr = null;
        if (this.minValue != null) {
            minMaxValueExpr = new Expression<Object>(this.getName()).greaterOrEqual(minValue);
        }
        if (this.maxValue != null) {
            Expression<Object> maxValueExpr = new Expression<Object>(this.getName()).lessOrEqual(maxValue);
            if (minMaxValueExpr == null) {
                minMaxValueExpr = maxValueExpr;
            } else {
                minMaxValueExpr = minMaxValueExpr.and(maxValueExpr);
            }
        }
        this.checkConstraint(minMaxValueExpr, value);
    }

    public Number getMinValue() {
        return this.minValue;
    }

    public Number getMaxValue() {
        return this.maxValue;
    }

    public void setMinValue(Number minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(Number maxValue) {
        this.maxValue = maxValue;
    }

}
