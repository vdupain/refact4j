package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.*;
import org.refact4j.expr.Expression;

@SuppressWarnings("serial")
public class ManyToOneRelationFieldImpl extends AbstractRelationFieldImpl implements ManyToOneRelationField {

    public ManyToOneRelationFieldImpl() {
    }

    public ManyToOneRelationFieldImpl(String fieldName, String prettyName, EntityDescriptor entityDescriptor,
                                      EntityDescriptor targetEntityDesc, OneToManyRelationField inverseRelationField, Key defaultValue,
                                      boolean nullable, boolean visible, boolean editable, Integer order, Property property,
                                      Expression<Key> constraint) {
        super(fieldName, prettyName, entityDescriptor, targetEntityDesc, inverseRelationField, defaultValue, nullable,
                visible, editable, order, property, constraint);
    }

    public void accept(DataTypeVisitor dataTypeVisitor) {
        dataTypeVisitor.visitManyToOneRelationType(this);
    }

    public void accept(FieldVisitor fieldVisitor) {
        fieldVisitor.visitManyToOneRelationField(this);
    }

    public Class<Key> getDataTypeClass() {
        return Key.class;
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.DATA_TYPE, DataTypeType.MANY_TO_ONE_RELATION_DATA_TYPE);
        return entity;
    }

}
