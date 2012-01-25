package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.DataTypeVisitor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.FieldVisitor;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.OneToOneRelationField;
import org.refact4j.eom.model.Property;
import org.refact4j.expr.Expression;

@SuppressWarnings("serial")
public class OneToOneRelationFieldImpl extends AbstractRelationFieldImpl implements OneToOneRelationField {

    public OneToOneRelationFieldImpl() {
    }

    public OneToOneRelationFieldImpl(String fieldName, String prettyName, EntityDescriptor entityDescriptor,
                                     EntityDescriptor targetEntityDesc, OneToOneRelationField inverseRelationField, Key defaultValue,
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