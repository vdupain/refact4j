package org.refact4j.eom.metamodel;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.*;

import java.util.function.Predicate;

public class FieldDesc {

    private static final EntityDescriptorBuilder BUILDER = EntityDescriptorBuilder.init("field");

    public static final EntityDescriptor INSTANCE = BUILDER.get();

    public static final StringField NAME = FieldFactory.init(BUILDER, "name").createStringField();

    public static final BooleanField NULLABLE = FieldFactory.init(BUILDER, "nullable").setDefaultValue(true)
            .createBooleanField();

    public static final StringField DEFAULT_VALUE = FieldFactory.init(BUILDER, "defaultValue").createStringField();

    public static final StringField MIN_VALUE = FieldFactory.init(BUILDER, "minValue").createStringField();

    public static final StringField MAX_VALUE = FieldFactory.init(BUILDER, "maxValue").createStringField();

    public static final StringField PRETTY_NAME = FieldFactory.init(BUILDER, "prettyName").createStringField();

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

    static {
        BUILDER.addKeyField(NAME);
        BUILDER.addKeyField(ENTITY_DESC);
    }

    private FieldDesc() {
    }


    public static Predicate<EntityObject> getAllFieldsForEntityDescriptor(final String entityDescName) {
        return arg -> {
            Key key = KeyBuilder.init(EntityDescriptorDesc.INSTANCE).set(EntityDescriptorDesc.NAME, entityDescName)
                    .get();
            return arg.getEntityDescriptor().equals(FieldDesc.INSTANCE)
                    && key.equals(arg.get(FieldDesc.ENTITY_DESC));
        };
    }

    public static Predicate<EntityObject> getFieldsForEntityDescriptor(final String entityDescName) {
        return arg -> {

            Key key = KeyBuilder.init(EntityDescriptorDesc.INSTANCE).set(EntityDescriptorDesc.NAME, entityDescName)
                    .get();
            return arg.getEntityDescriptor().equals(FieldDesc.INSTANCE)
                    && key.equals(arg.get(FieldDesc.ENTITY_DESC))
                    && !arg.get(FieldDesc.DATA_TYPE).equals(DataTypeType.MANY_TO_ONE_RELATION_DATA_TYPE.getKey())
                    && !arg.get(FieldDesc.DATA_TYPE).equals(DataTypeType.ONE_TO_MANY_RELATION_DATA_TYPE.getKey());
        };
    }

    public static Predicate<EntityObject> getRelationFieldsForEntityDescriptor(final String entityDescName) {
        return arg -> {
            Key key = KeyBuilder.init(EntityDescriptorDesc.INSTANCE).set(EntityDescriptorDesc.NAME, entityDescName)
                    .get();
            return arg.getEntityDescriptor().equals(FieldDesc.INSTANCE)
                    && key.equals(arg.get(FieldDesc.ENTITY_DESC))
                    && (arg.get(FieldDesc.DATA_TYPE).equals(DataTypeType.MANY_TO_ONE_RELATION_DATA_TYPE.getKey()) || arg
                    .get(FieldDesc.DATA_TYPE).equals(DataTypeType.ONE_TO_MANY_RELATION_DATA_TYPE.getKey()));
        };
    }

}
