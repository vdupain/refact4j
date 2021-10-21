package org.refact4j.eom.model.impl;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.NumericField;

public abstract class AbstractNumberField extends AbstractField implements NumericField {

    AbstractNumberField() {
    }

    AbstractNumberField(String fieldName, String prettyName, EntityDescriptor entityDesc, Number defaultValue,
                        boolean nullable) {
        super(fieldName, prettyName, entityDesc, defaultValue, nullable);
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        return entity;
    }

}
