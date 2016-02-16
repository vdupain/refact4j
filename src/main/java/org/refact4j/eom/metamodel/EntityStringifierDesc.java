package org.refact4j.eom.metamodel;

import org.refact4j.eom.model.*;

public class EntityStringifierDesc {

    private static final EntityDescriptorBuilder BUILDER = EntityDescriptorBuilder.init("stringifier");

    public static final EntityDescriptor INSTANCE = BUILDER.get();

    public static final StringField NAME = FieldFactory.init(BUILDER, "name").createStringField();

    public static final ManyToOneRelationField OBJECT_TYPE = FieldFactory.init(BUILDER, "entityDescriptor")
            .createManyToOneRelationField(EntityDescriptorDesc.INSTANCE);

    public static final OneToManyRelationField APPENDERS = FieldFactory.init(BUILDER, "appenders")
            .createOneToManyRelationField(EntityStringifierAppenderDesc.INSTANCE);

    static {
        BUILDER.addKeyField(NAME);
        BUILDER.addKeyField(OBJECT_TYPE);
    }

}
