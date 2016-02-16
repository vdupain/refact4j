package org.refact4j.model;

import org.refact4j.eom.model.*;

class EmployeeDesc {

    private static final EntityDescriptorBuilder builder = EntityDescriptorBuilder.init("Employee");

    public static final EntityDescriptor INSTANCE = builder.getEntityDescriptor();
    public static final OneToOneRelationField INFO = FieldFactory.init(builder, "info").createOneToOneRelationField(
            EmployeeInfoDesc.INSTANCE);
    private static final IntegerField ID = FieldFactory.init(builder, "id").createIntegerField();

    static {
        builder.addKeyField(ID);
    }

}