package org.refact4j.model;

import org.refact4j.eom.model.*;
import org.refact4j.expr.ExpressionBuilder;

import java.util.Date;


public class FooDesc {

    private static final EntityDescriptorBuilder builder = EntityDescriptorBuilder
            .init("Foo");

    public static final EntityDescriptor INSTANCE = builder.get();

    public static final IntegerField ID = FieldFactory.init(builder, "id")
            .setOrder(6).createIntegerField();

    public static final StringField NAME = FieldFactory.init(builder, "name")
            .setOrder(2).setPrettyName("The Name").setNullable(false)
            .setDefaultValue("").setMaxLength(20).createStringField();

    public static final DoubleField VALUE = FieldFactory.init(builder, "value")
            .setOrder(4).setNullable(true).createDoubleField();

    public static final DateField BEGIN_DATE = FieldFactory.init(builder,
            "beginDate").setOrder(3).setDefaultValue(new Date())
            .createDateField();

    public static final DateField END_DATE = FieldFactory.init(builder,
            "endDate").setOrder(7).createDateField();

    public static final DateField TIMESTAMP = FieldFactory.init(builder,
            "timestampDate").setOrder(8).isTimestamp(true).createDateField();

    public static final BooleanField FLAG = FieldFactory.init(builder, "flag")
            .setOrder(1).createBooleanField();


    public static final ManyToOneRelationField BAR = FieldFactory.init(
            builder, "bar").setOrder(5).createManyToOneRelationField(
            BarDesc.INSTANCE);

    public static final ManyToOneRelationField BAR_2 = FieldFactory.init(
            builder, "bar2").setOrder(9)
            .createManyToOneRelationField(BarDesc.INSTANCE);


    static {
        builder.addKeyField(ID);
        builder.setConstraint(ExpressionBuilder.init(ID).between(0, 10).and(
                ExpressionBuilder.init(VALUE).greaterOrEqual(0.))
                .get());

    }

}
