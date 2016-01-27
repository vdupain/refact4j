package org.refact4j.eom.metamodel;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityPredicate;
import org.refact4j.eom.model.*;

public class FieldDesc {

    private static final EntityDescriptorBuilder BUILDER = EntityDescriptorBuilder.init("field");

    public static final EntityDescriptor INSTANCE = BUILDER.getEntityDescriptor();

    public static final StringField NAME = FieldFactory.init(BUILDER, "name").createStringField();

    public static final BooleanField NULLABLE = FieldFactory.init(BUILDER, "nullable").setDefaultValue(true)
            .createBooleanField();

    public static final BooleanField VISIBLE = FieldFactory.init(BUILDER, "visible").setDefaultValue(true)
            .createBooleanField();

    public static final BooleanField EDITABLE = FieldFactory.init(BUILDER, "editable").setDefaultValue(true)
            .createBooleanField();

    public static final StringField DEFAULT_VALUE = FieldFactory.init(BUILDER, "defaultValue").createStringField();

    public static final StringField MIN_VALUE = FieldFactory.init(BUILDER, "minValue").createStringField();

    public static final StringField MAX_VALUE = FieldFactory.init(BUILDER, "maxValue").createStringField();

    public static final StringField PRETTY_NAME = FieldFactory.init(BUILDER, "prettyName").createStringField();

    public static final IntegerField ORDER = FieldFactory.init(BUILDER, "order").createIntegerField();

    public static final IntegerField MAX_LENGTH = FieldFactory.init(BUILDER, "maxLength").createIntegerField();

    public static final IntegerField MIN_LENGTH = FieldFactory.init(BUILDER, "minLength").createIntegerField();

    public static final ManyToOneRelationField ENTITY_DESC = FieldFactory.init(BUILDER, "entityDescriptor")
            .createManyToOneRelationField(EntityDescriptorDesc.INSTANCE);

    public static final BooleanField IS_KEY = FieldFactory.init(BUILDER, "isKey").setDefaultValue(false)
            .createBooleanField();

    public static final ManyToOneRelationField DATA_TYPE = FieldFactory.init(BUILDER, "dataType").createManyToOneRelationField(
            DataTypeType.INSTANCE);

    public static final ManyToOneRelationField TARGET = FieldFactory.init(BUILDER, "target").createManyToOneRelationField(
            EntityDescriptorDesc.INSTANCE);

    public static final ManyToOneRelationField INVERSE_RELATION_FIELD = FieldFactory.init(BUILDER, "inverseRelation")
            .createManyToOneRelationField(FieldDesc.INSTANCE);

    public static final OneToManyRelationField FIELD_TYPE = FieldFactory.init(BUILDER, "properties")
            .createOneToManyRelationField(PropertyDesc.INSTANCE);

    static {
        BUILDER.addKeyField(NAME);
        BUILDER.addKeyField(ENTITY_DESC);
    }

    private FieldDesc() {
    }


    public static EntityPredicate getAllFieldsForEntityDescriptor(final String entityDescName) {
        return new EntityPredicate() {
            public Boolean apply(EntityObject arg) {
                Key key = KeyBuilder.init(EntityDescriptorDesc.INSTANCE).set(EntityDescriptorDesc.NAME, entityDescName)
                        .getKey();
                return key.equals(arg.get(FieldDesc.ENTITY_DESC));
            }
        };
    }

    public static EntityPredicate getFieldsForEntityDescriptor(final String entityDescName) {
        return new EntityPredicate() {
            public Boolean apply(EntityObject arg) {
                Key key = KeyBuilder.init(EntityDescriptorDesc.INSTANCE).set(EntityDescriptorDesc.NAME, entityDescName)
                        .getKey();
                return key.equals(arg.get(FieldDesc.ENTITY_DESC))
                        && !arg.get(FieldDesc.DATA_TYPE).equals(DataTypeType.MANY_TO_ONE_RELATION_DATA_TYPE.getKey())
                        && !arg.get(FieldDesc.DATA_TYPE).equals(DataTypeType.ONE_TO_MANY_RELATION_DATA_TYPE.getKey());
            }
        };
    }

    public static EntityPredicate getRelationFieldsForEntityDescriptor(final String entityDescName) {
        return new EntityPredicate() {
            public Boolean apply(EntityObject arg) {
                Key key = KeyBuilder.init(EntityDescriptorDesc.INSTANCE).set(EntityDescriptorDesc.NAME, entityDescName)
                        .getKey();
                return key.equals(arg.get(FieldDesc.ENTITY_DESC))
                        && (arg.get(FieldDesc.DATA_TYPE).equals(DataTypeType.MANY_TO_ONE_RELATION_DATA_TYPE.getKey()) || arg
                        .get(FieldDesc.DATA_TYPE).equals(DataTypeType.ONE_TO_MANY_RELATION_DATA_TYPE.getKey()));
            }
        };
    }

}
