package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.*;
import org.refact4j.expr.Expression;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;

public abstract class AbstractRelationField extends AbstractField implements RelationField {

    private EntityDescriptor targetEntityDescriptor;

    private RelationField inverseRelationField;

    AbstractRelationField() {
    }

    AbstractRelationField(String fieldName, String prettyName, EntityDescriptor entityDescriptor,
                          EntityDescriptor targetEntityDescriptor, RelationField inverseRelationField, Object defaultValue,
                          boolean nullable, boolean visible, boolean editable, Integer order,
                          Expression<?> constraint) {
        super(fieldName, prettyName, entityDescriptor, defaultValue, nullable, visible, editable, order,
                constraint);
        this.targetEntityDescriptor = targetEntityDescriptor;
        this.inverseRelationField = inverseRelationField;
    }

    public EntityDescriptor getTargetEntityDescriptor() {
        return targetEntityDescriptor;
    }

    public RelationField getInverseRelationField() {
        RelationField inverseRel = null;
        if (this.inverseRelationField == null) {
            Collection<RelationField> relationFields = this.targetEntityDescriptor.getRelationFields();
            for (RelationField relationField : relationFields) {
                if (!isReciprocalToRelationField(relationField)) {
                    continue;
                }
                inverseRel = relationField;
                break;
            }
            if (inverseRel == null) {
                inverseRel = this;
            }
            this.inverseRelationField = inverseRel;
        }
        return this.inverseRelationField != this ? this.inverseRelationField : null;
    }

    public void setInverseRelationField(RelationField inverseRelationfield) {
        this.inverseRelationField = inverseRelationfield;
    }

    private boolean isReciprocalToRelationField(RelationField relationField) {
        if (!this.getEntityDescriptor().equals(relationField.getTargetEntityDescriptor())) {
            return false;
        }
        if (relationField instanceof ManyToOneRelationField && this instanceof OneToManyRelationField) {
            return true;
        } else if (relationField instanceof OneToManyRelationField && this instanceof ManyToOneRelationField) {
            return true;
        } else if (relationField instanceof OneToOneRelationField && this instanceof OneToOneRelationField) {
            return true;
        }
        return false;
    }

    @Override
    public EntityObject toEntity() {
        Key key = null;
        if (this.inverseRelationField != null) {
            key = KeyBuilder.init(FieldDesc.INSTANCE).set(FieldDesc.ENTITY_DESC,
                    this.targetEntityDescriptor.toEntity().getKey())
                    .set(FieldDesc.NAME, inverseRelationField.getName()).getKey();
        }
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.TARGET, this.getTargetEntityDescriptor().toEntity()).set(FieldDesc.INVERSE_RELATION_FIELD,
                key);
        return entity;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        this.targetEntityDescriptor = (EntityDescriptor) in.readObject();
        this.inverseRelationField = (RelationField) in.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(this.targetEntityDescriptor);
        out.writeObject(this.inverseRelationField);
    }

}
