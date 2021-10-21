package org.refact4j.eom.model;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.impl.IntegerFieldImpl;

import java.util.Collection;
import java.util.Date;

public final class FieldFactory implements DataTypeVisitor {

    private final String fieldName;
    private final EntityDescriptorBuilder entityDescriptorBuilder;
    private boolean isTimestamp;
    private boolean nullable = true;
    private Object defaultValue;
    private Number minValue;
    private Number maxValue;
    private String prettyName;
    private Integer order;
    private Field field;

    private FieldFactory(String fieldName, EntityDescriptorBuilder entityDescriptorBuilder) {
        this.fieldName = fieldName;
        this.entityDescriptorBuilder = entityDescriptorBuilder;
    }

    public static FieldFactory init(EntityDescriptorBuilder entityDescriptorBuilder, String fieldName) {
        return new FieldFactory(fieldName, entityDescriptorBuilder);
    }

    public StringField createStringField() {
        org.refact4j.eom.model.impl.StringField field = new org.refact4j.eom.model.impl.StringField(fieldName, prettyName, entityDescriptorBuilder
                .get(), (String) defaultValue, nullable
        );
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public DoubleField createDoubleField() {
        org.refact4j.eom.model.impl.DoubleField field = new org.refact4j.eom.model.impl.DoubleField(fieldName, prettyName, entityDescriptorBuilder
                .get(), (Double) defaultValue, nullable, order
        );
        field.setMaxValue(maxValue);
        field.setMinValue(minValue);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public IntegerField createIntegerField() {
        IntegerFieldImpl field = new IntegerFieldImpl(fieldName, prettyName, entityDescriptorBuilder
                .get(), (Integer) defaultValue, nullable, order
        );
        field.setMaxValue(maxValue);
        field.setMinValue(minValue);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public DateField createDateField() {
        DateField field = new org.refact4j.eom.model.impl.DateField(fieldName, prettyName, entityDescriptorBuilder.get(),
                isTimestamp, (Date) defaultValue, nullable);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public BooleanField createBooleanField() {
        BooleanField field = new org.refact4j.eom.model.impl.BooleanField(fieldName, prettyName, entityDescriptorBuilder.get(),
                (Boolean) defaultValue, nullable);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public ManyToOneRelationField createManyToOneRelationField(EntityDescriptor entityDescriptor) {
        ManyToOneRelationField field = new org.refact4j.eom.model.impl.ManyToOneRelationField(fieldName, prettyName, entityDescriptorBuilder
                .get(), entityDescriptor, null, (Key) defaultValue, nullable, order
        );
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public OneToManyRelationField createOneToManyRelationField(EntityDescriptor entityDescriptor) {
        OneToManyRelationField field = new org.refact4j.eom.model.impl.OneToManyRelationField(fieldName, prettyName, entityDescriptorBuilder
                .get(), entityDescriptor, null, (Collection<EntityObject>) defaultValue, nullable,
                order);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public OneToOneRelationField createOneToOneRelationField(EntityDescriptor entityDescriptor) {
        OneToOneRelationField field = new org.refact4j.eom.model.impl.OneToOneRelationField(fieldName, prettyName, entityDescriptorBuilder
                .get(), entityDescriptor, null, (Key) defaultValue, nullable,
                order);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public FieldFactory setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public FieldFactory setMinValue(Number minValue) {
        this.minValue = minValue;
        return this;
    }

    public FieldFactory setMaxValue(Number maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public FieldFactory setNullable(boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public FieldFactory isTimestamp(boolean isTimestamp) {
        this.isTimestamp = isTimestamp;
        return this;
    }

    public FieldFactory setPrettyName(String prettyName) {
        this.prettyName = prettyName;
        return this;
    }

    public FieldFactory setOrder(Integer order) {
        this.order = order;
        return this;
    }


    public void visitIntegerType(IntegerType integerType) {
        field = this.createIntegerField();
    }

    public void visitStringType(StringType stringType) {
        field = this.createStringField();
    }

    public void visitDoubleType(DoubleType doubleType) {
        field = this.createDoubleField();
    }

    public void visitDateType(DateType dateType) {
        field = this.createDateField();
    }

    public void visitBooleanType(BooleanType booleanType) {
        field = this.createBooleanField();
    }

    public void visitManyToOneRelationType(ManyToOneRelationType manyToOneRelationType) {
    }

    public void visitOneToManyRelationType(OneToManyRelationType oneToManyRelationType) {
    }

    public void visitOneToOneRelationType(OneToOneRelationType oneToOneRelationType) {
    }

    public Field get() {
        return field;
    }

}
