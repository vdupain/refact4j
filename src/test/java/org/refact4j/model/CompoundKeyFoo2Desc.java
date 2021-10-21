package org.refact4j.model;

import org.refact4j.eom.model.*;

import java.util.Date;

class CompoundKeyFoo2Desc {

    private static final EntityDescriptorBuilder builder = EntityDescriptorBuilder.init("CompoundKeyFoo2Desc");

    public static final EntityDescriptor INSTANCE = builder.get();
    public static final DateField END_DATE = FieldFactory.init(builder, "endDate").createDateField();
    public static final DateField TIMESTAMP = FieldFactory.init(builder, "timestampDate").isTimestamp(true)
            .createDateField();
    public static final BooleanField FLAG = FieldFactory.init(builder, "flag").createBooleanField();
    public static final ManyToOneRelationField BAR_2 = FieldFactory.init(builder, "bar2")
            .createManyToOneRelationField(BarDesc.INSTANCE);
    private static final IntegerField ID = FieldFactory.init(builder, "id").createIntegerField();
    private static final StringField NAME = FieldFactory.init(builder, "name").setPrettyName("The Name")
            .setNullable(false).setDefaultValue("").createStringField();
    private static final DoubleField VALUE = FieldFactory.init(builder, "value").setNullable(true)
            .createDoubleField();
    private static final DateField BEGIN_DATE = FieldFactory.init(builder, "beginDate").setDefaultValue(
            new Date()).createDateField();
    private static final ManyToOneRelationField BAR = FieldFactory.init(builder, "bar")
            .createManyToOneRelationField(BarDesc.INSTANCE);

    static {
        builder.addKeyField(ID);
        builder.addKeyField(BEGIN_DATE);
        builder.addKeyField(NAME);
        builder.addKeyField(VALUE);
        builder.addKeyField(BAR);

    }

}
