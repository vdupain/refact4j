package org.refact4j.eom.metamodel;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityPredicate;
import org.refact4j.eom.model.*;
import org.refact4j.visitor.AbstractVisitor;

import java.util.Date;

public final class PropertyDesc {

    private static final EntityDescriptorBuilder BUILDER = EntityDescriptorBuilder.init("property");

    public static final EntityDescriptor INSTANCE = BUILDER.getEntityDescriptor();

    public static final StringField KEY = FieldFactory.init(BUILDER, "key").createStringField();

    public static final StringField VALUE = FieldFactory.init(BUILDER, "value").createStringField();

    public static final ManyToOneRelationField DATA_TYPE = FieldFactory.init(BUILDER, "dataType").createManyToOneRelationField(
            DataTypeType.INSTANCE);

    public static final ManyToOneRelationField FIELD_TYPE = FieldFactory.init(BUILDER, "field").createManyToOneRelationField(
            FieldDesc.INSTANCE);

    public static final ManyToOneRelationField ENTITY_DESC_DESC = FieldFactory.init(BUILDER, "entityDescriptor")
            .createManyToOneRelationField(EntityDescriptorDesc.INSTANCE);

    static {
        BUILDER.addKeyField(KEY);
        BUILDER.addKeyField(FIELD_TYPE);
        BUILDER.addKeyField(ENTITY_DESC_DESC);
    }

    public static EntityPredicate getPropertiesForField(final Field field) {
        return new EntityPredicate() {
            public Boolean apply(EntityObject arg) {
                Key key = KeyBuilder.init(FieldDesc.INSTANCE).set(FieldDesc.NAME, field.getName()).set(
                        FieldDesc.ENTITY_DESC, field.getEntityDescriptor().toEntity().getKey()).getKey();
                return key.equals(arg.get(PropertyDesc.FIELD_TYPE));
            }
        };
    }

    public static EntityPredicate getPropertiesForEntityDescriptor(final EntityDescriptor entityDescriptor) {
        return new EntityPredicate() {
            public Boolean apply(EntityObject arg) {
                return entityDescriptor.toEntity().getKey().equals(arg.get(PropertyDesc.ENTITY_DESC_DESC));
            }
        };
    }

    public static class TypeVisitor extends AbstractVisitor {
        public DataTypeEntity dataTypeEntity = DataTypeType.STRING_DATA_TYPE;

        public void visitInteger(Integer value) {
            this.dataTypeEntity = DataTypeType.INTEGER_DATA_TYPE;
        }

        public void visitString(String value) {
            this.dataTypeEntity = DataTypeType.STRING_DATA_TYPE;
        }

        public void visitBoolean(Boolean value) {
            this.dataTypeEntity = DataTypeType.BOOLEAN_DATA_TYPE;
        }

        public void visitDate(Date value) {
            this.dataTypeEntity = DataTypeType.DATE_DATA_TYPE;
        }

        public void visitDouble(Double value) {
            this.dataTypeEntity = DataTypeType.DOUBLE_DATA_TYPE;
        }

    }

    static class KeyValuePair {
        public String key;

        public Object value;
    }
}
