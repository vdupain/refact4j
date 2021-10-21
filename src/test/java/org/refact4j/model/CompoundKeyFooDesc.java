package org.refact4j.model;

import org.refact4j.eom.model.*;

public class CompoundKeyFooDesc {

    private static final EntityDescriptorBuilder builder = EntityDescriptorBuilder
            .init("CompoundKeyFooDesc");

    public static final EntityDescriptor INSTANCE = builder.get();

    public static final IntegerField ID = FieldFactory.init(builder, "id")
            .createIntegerField();

    public static final StringField NAME = FieldFactory.init(builder, "name")
            .setDefaultValue("").createStringField();

    public static final DoubleField VALUE = FieldFactory.init(builder, "value")
            .createDoubleField();

    public static final DateField DATE = FieldFactory.init(builder, "date")
            .createDateField();

    static {
        builder.addKeyField(ID);
        builder.addKeyField(NAME);
    }
}
