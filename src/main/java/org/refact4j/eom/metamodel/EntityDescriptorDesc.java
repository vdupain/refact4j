package org.refact4j.eom.metamodel;

import org.refact4j.eom.model.*;

public class EntityDescriptorDesc {

    private static final EntityDescriptorBuilder BUILDER = EntityDescriptorBuilder.init("entityDescriptor");

    public static final EntityDescriptor INSTANCE = BUILDER.getEntityDescriptor();

    public static final StringField NAME = FieldFactory.init(BUILDER, "name").createStringField();

    public static final OneToManyRelationField FIELDS = FieldFactory.init(BUILDER, "fields").createOneToManyRelationField(
            FieldDesc.INSTANCE);

    // public static final ManyToOneRelationField STRINGIFIER =
    // FieldFactory.init(BUILDER, "stringifier")
    // .createManyToOneRelationField(EntityStringifierDesc.INSTANCE);

    static {
        BUILDER.addKeyField(NAME);
    }
}
