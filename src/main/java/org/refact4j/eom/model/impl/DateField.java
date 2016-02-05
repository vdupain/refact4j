package org.refact4j.eom.model.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.DataTypeVisitor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.FieldVisitor;
import org.refact4j.eom.model.Property;
import org.refact4j.expr.Expression;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

@SuppressWarnings("serial")
public class DateField extends AbstractField implements org.refact4j.eom.model.DateField {

    private boolean isTimestamp;

    public DateField() {
    }

    public DateField(String fieldName, String prettyName, EntityDescriptor entityDescriptor, boolean isTimestamp,
                     Date defaultValue, boolean nullable, boolean visible, boolean editable, Integer order,
                     Expression<? extends Date> constraint) {
        super(fieldName, prettyName, entityDescriptor, defaultValue, nullable, visible, editable, order,
                constraint);
        this.isTimestamp = isTimestamp;
    }

    public void accept(DataTypeVisitor dataTypeVisitor) {
        dataTypeVisitor.visitDateType(this);
    }

    public void accept(FieldVisitor fieldVisitor) {
        fieldVisitor.visitDateField(this);
    }

    protected Class<Date> getDataTypeClass() {
        return Date.class;
    }

    public boolean isTimestamp() {
        return this.isTimestamp;
    }

    @Override
    public EntityObject toEntity() {
        EntityObject entity = super.toEntity();
        entity.set(FieldDesc.DATA_TYPE, DataTypeType.DATE_DATA_TYPE);
        return entity;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        this.isTimestamp = in.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeBoolean(this.isTimestamp);
    }

}
