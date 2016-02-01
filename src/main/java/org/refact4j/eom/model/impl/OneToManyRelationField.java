package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.DataTypeVisitor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.FieldVisitor;
import org.refact4j.eom.model.Property;
import org.refact4j.expr.Expression;

import java.util.Collection;

@SuppressWarnings("serial")
public class OneToManyRelationField extends AbstractRelationField implements org.refact4j.eom.model.OneToManyRelationField {

    public OneToManyRelationField() {
    }

    public OneToManyRelationField(String fieldName, String prettyName, EntityDescriptor entityDescriptor,
                                  EntityDescriptor targetEntityDescriptor, ManyToOneRelationField inverseRelationField,
                                  Collection<EntityObject> defaultValue, boolean nullable, boolean visible, boolean editable, Integer order,
                                  Property property, Expression<Collection<EntityObject>> constraint) {
        super(fieldName, prettyName, entityDescriptor, targetEntityDescriptor, inverseRelationField, defaultValue,
                nullable, visible, editable, order, property, constraint);
    }

    public void accept(DataTypeVisitor dataTypeVisitor) {
        dataTypeVisitor.visitOneToManyRelationType(this);
    }

    public void accept(FieldVisitor fieldVisitor) {
        fieldVisitor.visitOneToManyRelationField(this);
    }

    @Override
    protected Class<Collection> getDataTypeClass() {
        return Collection.class;
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.DATA_TYPE, DataTypeType.ONE_TO_MANY_RELATION_DATA_TYPE);
        return entity;
    }

}
