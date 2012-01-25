package org.refact4j.model;

import org.refact4j.eom.model.DateField;
import org.refact4j.eom.model.DoubleField;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorBuilder;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.model.IntegerField;
import org.refact4j.eom.model.StringField;

public class CompoundKeyFooDesc {

    private static final EntityDescriptorBuilder builder = EntityDescriptorBuilder
            .init("CompoundKeyFooDesc");

    public static final EntityDescriptor INSTANCE = builder.getEntityDescriptor();

    public static final IntegerField ID = FieldFactory.init(builder, "id")
            .createIntegerField();

    public static final StringField NAME = FieldFactory.init(builder, "name")
            .setDefaultValue("").setMaxLength(20).createStringField();

    public static final DoubleField VALUE = FieldFactory.init(builder, "value")
            .createDoubleField();

    public static final DateField DATE = FieldFactory.init(builder, "date")
            .createDateField();

    static {
        builder.addKeyField(ID);
        builder.addKeyField(NAME);
    }
}
