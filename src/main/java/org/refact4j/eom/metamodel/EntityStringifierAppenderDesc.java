package org.refact4j.eom.metamodel;

import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorBuilder;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.model.IntegerField;
import org.refact4j.eom.model.ManyToOneRelationField;
import org.refact4j.eom.model.StringField;

public class EntityStringifierAppenderDesc {

    private static final EntityDescriptorBuilder BUILDER = EntityDescriptorBuilder.init("append");

    public static final EntityDescriptor INSTANCE = BUILDER.getEntityDescriptor();

    public static final IntegerField ID = FieldFactory.init(BUILDER, "id").createIntegerField();

    public static final ManyToOneRelationField STRINGIFIER = FieldFactory.init(BUILDER, "stringifier")
            .createManyToOneRelationField(EntityStringifierDesc.INSTANCE);

    public static final ManyToOneRelationField FIELD = FieldFactory.init(BUILDER, "field").createManyToOneRelationField(
            FieldDesc.INSTANCE);

    public static final StringField STRING = FieldFactory.init(BUILDER, "string").createStringField();

    static {
        BUILDER.addKeyField(ID);
        BUILDER.addKeyField(STRINGIFIER);
    }

}
