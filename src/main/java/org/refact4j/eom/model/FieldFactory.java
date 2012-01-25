package org.refact4j.eom.model;

import org.refact4j.eom.EntityCollection;
import org.refact4j.eom.model.impl.BooleanFieldImpl;
import org.refact4j.eom.model.impl.DateFieldImpl;
import org.refact4j.eom.model.impl.DoubleFieldImpl;
import org.refact4j.eom.model.impl.IntegerFieldImpl;
import org.refact4j.eom.model.impl.ManyToOneRelationFieldImpl;
import org.refact4j.eom.model.impl.OneToManyRelationFieldImpl;
import org.refact4j.eom.model.impl.OneToOneRelationFieldImpl;
import org.refact4j.eom.model.impl.PropertyImpl;
import org.refact4j.eom.model.impl.StringFieldImpl;
import org.refact4j.expr.Expression;

import java.util.Date;

public final class FieldFactory implements DataTypeVisitor {

    private final String fieldName;

    private Integer maxLength;

    private Integer minLength;

    private boolean isTimestamp;

    private boolean nullable = true;

    private boolean visible;

    private boolean editable;

    private final EntityDescriptorBuilder entityDescriptorBuilder;

    private Object defaultValue;

    private Number minValue;

    private Number maxValue;

    private Expression constraintExpression;

    private String prettyName;

    private Integer order;

    private final Property property = new PropertyImpl();

    private Field field;

    private FieldFactory(String fieldName, EntityDescriptorBuilder entityDescriptorBuilder) {
        this.fieldName = fieldName;
        this.entityDescriptorBuilder = entityDescriptorBuilder;
    }

    public static FieldFactory init(EntityDescriptorBuilder entityDescriptorBuilder, String fieldName) {
        return new FieldFactory(fieldName, entityDescriptorBuilder);
    }

    public StringField createStringField() {
        StringFieldImpl field = new StringFieldImpl(fieldName, prettyName, entityDescriptorBuilder
                .getEntityDescriptor(), (String) defaultValue, nullable, visible, editable, order, property,
                constraintExpression);
        field.setMaxLength(maxLength);
        field.setMinLength(minLength);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public DoubleField createDoubleField() {
        DoubleFieldImpl field = new DoubleFieldImpl(fieldName, prettyName, entityDescriptorBuilder
                .getEntityDescriptor(), (Double) defaultValue, nullable, visible, editable, order, property,
                constraintExpression);
        field.setMaxValue(maxValue);
        field.setMinValue(minValue);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public IntegerField createIntegerField() {
        IntegerFieldImpl field = new IntegerFieldImpl(fieldName, prettyName, entityDescriptorBuilder
                .getEntityDescriptor(), (Integer) defaultValue, nullable, visible, editable, order, property,
                constraintExpression);
        field.setMaxValue(maxValue);
        field.setMinValue(minValue);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public DateField createDateField() {
        DateField field = new DateFieldImpl(fieldName, prettyName, entityDescriptorBuilder.getEntityDescriptor(),
                isTimestamp, (Date) defaultValue, nullable, visible, editable, order, property, constraintExpression);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public BooleanField createBooleanField() {
        BooleanField field = new BooleanFieldImpl(fieldName, prettyName, entityDescriptorBuilder.getEntityDescriptor(),
                (Boolean) defaultValue, nullable, visible, editable, order, property, constraintExpression);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public ManyToOneRelationField createManyToOneRelationField(EntityDescriptor entityDescriptor) {
        ManyToOneRelationField field = new ManyToOneRelationFieldImpl(fieldName, prettyName, entityDescriptorBuilder
                .getEntityDescriptor(), entityDescriptor, null, (Key) defaultValue, nullable, visible, editable, order,
                property, constraintExpression);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public OneToManyRelationField createOneToManyRelationField(EntityDescriptor entityDescriptor) {
        OneToManyRelationField field = new OneToManyRelationFieldImpl(fieldName, prettyName, entityDescriptorBuilder
                .getEntityDescriptor(), entityDescriptor, null, (EntityCollection) defaultValue, nullable, visible,
                editable, order, property, constraintExpression);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public OneToOneRelationField createOneToOneRelationField(EntityDescriptor entityDescriptor) {
        OneToOneRelationField field = new OneToOneRelationFieldImpl(fieldName, prettyName, entityDescriptorBuilder
                .getEntityDescriptor(), entityDescriptor, null, (Key) defaultValue, nullable, visible,
                editable, order, property, constraintExpression);
        entityDescriptorBuilder.addField(field);
        return field;
    }

    public FieldFactory setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public FieldFactory setMinLength(Integer minLength) {
        this.minLength = minLength;
        return this;
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

    public FieldFactory setConstraintExpression(Expression<?> constraintExpression) {
        this.constraintExpression = constraintExpression;
        return this;
    }

    public FieldFactory setNullable(boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public FieldFactory setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public FieldFactory setEditable(boolean editable) {
        this.editable = editable;
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

    public FieldFactory addProperty(Object key, Object value) {
        this.property.addProperty(key, value);
        return this;
    }

    public void visitDataType(DataType dataType) {
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

    public Field getField() {
        return field;
    }

}
