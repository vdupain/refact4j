package org.refact4j.model;

import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorBuilder;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.model.IntegerField;
import org.refact4j.eom.model.OneToOneRelationField;
import org.refact4j.eom.model.StringField;

public class EmployeeInfoDesc {

    private static final EntityDescriptorBuilder builder = EntityDescriptorBuilder.init("EmployeeInfo");

    public static final EntityDescriptor INSTANCE = builder.getEntityDescriptor();

    public static final IntegerField ID = FieldFactory.init(builder, "id").createIntegerField();

    public static final StringField FIRST_NAME = FieldFactory.init(builder, "firstName").createStringField();

    public static final StringField LAST_NAME = FieldFactory.init(builder, "lastName").createStringField();

    public static final OneToOneRelationField EMPLOYEE = FieldFactory.init(builder, "employee").createOneToOneRelationField(
            EmployeeDesc.INSTANCE);

    static {
        builder.addKeyField(ID);
    }

}