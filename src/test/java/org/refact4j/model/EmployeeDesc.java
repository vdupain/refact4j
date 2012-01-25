package org.refact4j.model;

import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorBuilder;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.model.IntegerField;
import org.refact4j.eom.model.OneToOneRelationField;

public class EmployeeDesc {

    private static final EntityDescriptorBuilder builder = EntityDescriptorBuilder.init("Employee");

    public static final EntityDescriptor INSTANCE = builder.getEntityDescriptor();

    public static final IntegerField ID = FieldFactory.init(builder, "id").createIntegerField();

    public static final OneToOneRelationField INFO = FieldFactory.init(builder, "info").createOneToOneRelationField(
            EmployeeInfoDesc.INSTANCE);

    static {
        builder.addKeyField(ID);
    }

}