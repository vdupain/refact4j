package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.*;
import org.refact4j.expr.Expression;

@SuppressWarnings("serial")
public class OneToOneRelationField extends AbstractRelationField implements org.refact4j.eom.model.OneToOneRelationField {

    public OneToOneRelationField() {
    }

    public OneToOneRelationField(String fieldName, String prettyName, EntityDescriptor entityDescriptor,
                                 EntityDescriptor targetEntityDesc, org.refact4j.eom.model.OneToOneRelationField inverseRelationField, Key defaultValue,
                                 boolean nullable, boolean visible, boolean editable, Integer order, Property property,
                                 Expression<Key> constraint) {
        super(fieldName, prettyName, entityDescriptor, targetEntityDesc, inverseRelationField, defaultValue, nullable,
                visible, editable, order, property, constraint);
    }

    public void accept(DataTypeVisitor dataTypeVisitor) {
        dataTypeVisitor.visitOneToOneRelationType(this);
    }

    public void accept(FieldVisitor fieldVisitor) {
        fieldVisitor.visitOneToOneRelationField(this);
    }

    public Class<Key> getDataTypeClass() {
        return Key.class;
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.DATA_TYPE, DataTypeType.ONE_TO_ONE_RELATION_DATA_TYPE);
        return entity;
    }

}