package org.refact4j.model;

import org.refact4j.eom.model.*;

class EmployeeInfoDesc {

    private static final EntityDescriptorBuilder builder = EntityDescriptorBuilder.init("EmployeeInfo");

    public static final EntityDescriptor INSTANCE = builder.getEntityDescriptor();
    public static final StringField FIRST_NAME = FieldFactory.init(builder, "firstName").createStringField();
    public static final StringField LAST_NAME = FieldFactory.init(builder, "lastName").createStringField();
    public static final OneToOneRelationField EMPLOYEE = FieldFactory.init(builder, "employee").createOneToOneRelationField(
            EmployeeDesc.INSTANCE);
    private static final IntegerField ID = FieldFactory.init(builder, "id").createIntegerField();

    static {
        builder.addKeyField(ID);
    }

}