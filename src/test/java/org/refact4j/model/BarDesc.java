package org.refact4j.model;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.model.DoubleField;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorBuilder;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.model.IntegerField;
import org.refact4j.eom.model.OneToManyRelationField;
import org.refact4j.eom.model.StringField;

public class BarDesc {

    private static final EntityDescriptorBuilder builder = EntityDescriptorBuilder.init("Bar");

    public static final EntityDescriptor INSTANCE = builder.getEntityDescriptor();

    public static final IntegerField ID = FieldFactory.init(builder, "id").createIntegerField();

    public static final StringField NAME = FieldFactory.init(builder, "name").createStringField();

    public static final DoubleField VALUE = FieldFactory.init(builder, "value").createDoubleField();

    public static final OneToManyRelationField FOOS = FieldFactory.init(builder, "foos").createOneToManyRelationField(
            FooDesc.INSTANCE);

    static {
        builder.addKeyField(ID);
    }

    public static EntityStringifier STRINGIFIER = new EntityStringifier() {

        public String stringify(EntityObject arg) {
            return arg.get(NAME);
        }

    };

}
