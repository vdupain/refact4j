package org.refact4j.eom.model.impl;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.NumericField;

public abstract class AbstractNumberField extends AbstractField implements NumericField {

    private Number minValue;
    private Number maxValue;

    AbstractNumberField() {
    }

    AbstractNumberField(String fieldName, String prettyName, EntityDescriptor entityDesc, Number defaultValue,
                        boolean nullable, Integer order) {
        super(fieldName, prettyName, entityDesc, defaultValue, nullable, order);
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.MIN_VALUE, ConverterHelper.convertValue2String(maxValue, this)).set(FieldDesc.MAX_VALUE,
                ConverterHelper.convertValue2String(minValue, this));
        return entity;
    }

    public Number getMinValue() {
        return this.minValue;
    }

    public void setMinValue(Number minValue) {
        this.minValue = minValue;
    }

    public Number getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(Number maxValue) {
        this.maxValue = maxValue;
    }

}
