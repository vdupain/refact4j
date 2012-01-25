package org.refact4j.eom.model;

import org.refact4j.eom.impl.EntityObjectImpl;

public class DataTypeEntity extends EntityObjectImpl implements DataType {

    private StringField field;

    DataTypeEntity() {

    }

    DataTypeEntity(StringField field, String name, EntityDescriptorBuilder builder) {
        super(builder.getEntityDescriptor());
        this.field = field;
        setFieldValue(field, name);
    }

    public String getName() {
        return get(field);
    }

    public DataType getDataType() {
        return this;
    }

    public void accept(DataTypeVisitor dataTypeVisitor) {
        dataTypeVisitor.visitDataType(this);
    }

    public static class IntegerDataTypeEntity extends DataTypeEntity implements IntegerType {
        public IntegerDataTypeEntity() {
            super();
        }

        public IntegerDataTypeEntity(StringField field, String name, EntityDescriptorBuilder builder) {
            super(field, name, builder);
        }

        public void accept(DataTypeVisitor dataTypeVisitor) {
            dataTypeVisitor.visitIntegerType(this);
        }
    }

    public static class DoubleDataTypeEntity extends DataTypeEntity implements DoubleType {

        public DoubleDataTypeEntity() {
            super();
        }

        public DoubleDataTypeEntity(StringField field, String name, EntityDescriptorBuilder builder) {
            super(field, name, builder);
        }

        public void accept(DataTypeVisitor dataTypeVisitor) {
            dataTypeVisitor.visitDoubleType(this);
        }
    }

    public static class BooleanDataTypeEntity extends DataTypeEntity implements BooleanType {

        public BooleanDataTypeEntity() {
            super();
        }

        public BooleanDataTypeEntity(StringField field, String name, EntityDescriptorBuilder builder) {
            super(field, name, builder);
        }

        public void accept(DataTypeVisitor dataTypeVisitor) {
            dataTypeVisitor.visitBooleanType(this);
        }
    }

    public static class DateDataTypeEntity extends DataTypeEntity implements DateType {

        public DateDataTypeEntity() {
            super();
        }

        public DateDataTypeEntity(StringField field, String name, EntityDescriptorBuilder builder) {
            super(field, name, builder);
        }

        public void accept(DataTypeVisitor dataTypeVisitor) {
            dataTypeVisitor.visitDateType(this);
        }
    }

    public static class StringDataTypeEntity extends DataTypeEntity implements StringType {

        public StringDataTypeEntity() {
            super();
        }

        public StringDataTypeEntity(StringField field, String name, EntityDescriptorBuilder builder) {
            super(field, name, builder);
        }

        public void accept(DataTypeVisitor dataTypeVisitor) {
            dataTypeVisitor.visitStringType(this);
        }
    }

    public static class ToOneRelationDataTypeEntity extends DataTypeEntity implements ManyToOneRelationType {

        public ToOneRelationDataTypeEntity() {
            super();
        }

        public ToOneRelationDataTypeEntity(StringField field, String name, EntityDescriptorBuilder builder) {
            super(field, name, builder);
        }

        public void accept(DataTypeVisitor dataTypeVisitor) {
            dataTypeVisitor.visitManyToOneRelationType(this);
        }
    }

    public static class OneToManyRelationDataTypeEntity extends DataTypeEntity implements OneToManyRelationType {
        public OneToManyRelationDataTypeEntity() {
            super();
        }

        public OneToManyRelationDataTypeEntity(StringField field, String name, EntityDescriptorBuilder builder) {
            super(field, name, builder);
        }

        public void accept(DataTypeVisitor dataTypeVisitor) {
            dataTypeVisitor.visitOneToManyRelationType(this);
        }
    }

    public static class OneToOneRelationDataTypeEntity extends DataTypeEntity implements OneToOneRelationType {
        public OneToOneRelationDataTypeEntity() {
            super();
        }

        public OneToOneRelationDataTypeEntity(StringField field, String name, EntityDescriptorBuilder builder) {
            super(field, name, builder);
        }

        public void accept(DataTypeVisitor dataTypeVisitor) {
            dataTypeVisitor.visitOneToOneRelationType(this);
        }
    }

}
