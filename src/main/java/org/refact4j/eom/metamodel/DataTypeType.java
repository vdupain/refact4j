package org.refact4j.eom.metamodel;

import org.refact4j.eom.EntityCollection;
import org.refact4j.eom.impl.EntityList;
import org.refact4j.eom.model.*;


public class DataTypeType {

    public static final EntityCollection DATA_TYPES = new EntityList();
    private static final String INTEGER_TYPE = "integer";
    private static final String STRING_TYPE = "string";
    private static final String DATE_TYPE = "date";
    private static final String BOOLEAN_TYPE = "boolean";
    private static final String DOUBLE_TYPE = "double";
    private static final String MANY_TO_ONE_RELATION_TYPE = "manyToOne";
    private static final String ONE_TO_MANY_RELATION_TYPE = "oneToMany";
    private static final String ONE_TO_ONE_RELATION_TYPE = "oneToOne";
    private static final EntityDescriptorBuilder BUILDER = EntityDescriptorBuilder.init("dataType");
    public static final EntityDescriptor INSTANCE = BUILDER.getEntityDescriptor();
    public static final StringField NAME = FieldFactory.init(BUILDER, "name").createStringField();
    public static final DataTypeEntity INTEGER_DATA_TYPE = new DataTypeEntity.IntegerDataTypeEntity(NAME, INTEGER_TYPE,
            BUILDER);
    public static final DataTypeEntity DOUBLE_DATA_TYPE = new DataTypeEntity.DoubleDataTypeEntity(NAME, DOUBLE_TYPE,
            BUILDER);
    public static final DataTypeEntity BOOLEAN_DATA_TYPE = new DataTypeEntity.BooleanDataTypeEntity(NAME, BOOLEAN_TYPE,
            BUILDER);
    public static final DataTypeEntity DATE_DATA_TYPE = new DataTypeEntity.DateDataTypeEntity(NAME, DATE_TYPE, BUILDER);
    public static final DataTypeEntity STRING_DATA_TYPE = new DataTypeEntity.StringDataTypeEntity(NAME, STRING_TYPE,
            BUILDER);
    public static final DataTypeEntity MANY_TO_ONE_RELATION_DATA_TYPE = new DataTypeEntity.ToOneRelationDataTypeEntity(NAME,
            MANY_TO_ONE_RELATION_TYPE, BUILDER);
    public static final DataTypeEntity ONE_TO_MANY_RELATION_DATA_TYPE = new DataTypeEntity.OneToManyRelationDataTypeEntity(
            NAME, ONE_TO_MANY_RELATION_TYPE, BUILDER);
    public static final DataTypeEntity ONE_TO_ONE_RELATION_DATA_TYPE = new DataTypeEntity.OneToOneRelationDataTypeEntity(
            NAME, ONE_TO_ONE_RELATION_TYPE, BUILDER);

    static {
        BUILDER.addKeyField(NAME);
        DATA_TYPES.add(INTEGER_DATA_TYPE);
        DATA_TYPES.add(DOUBLE_DATA_TYPE);
        DATA_TYPES.add(BOOLEAN_DATA_TYPE);
        DATA_TYPES.add(DATE_DATA_TYPE);
        DATA_TYPES.add(STRING_DATA_TYPE);
        DATA_TYPES.add(MANY_TO_ONE_RELATION_DATA_TYPE);
        DATA_TYPES.add(ONE_TO_MANY_RELATION_DATA_TYPE);
        DATA_TYPES.add(ONE_TO_ONE_RELATION_DATA_TYPE);
    }

}
