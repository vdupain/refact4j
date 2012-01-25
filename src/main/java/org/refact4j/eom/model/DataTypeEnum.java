package org.refact4j.eom.model;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;

public enum DataTypeEnum {
    STRING(DataTypeType.STRING_DATA_TYPE), DATE(DataTypeType.DATE_DATA_TYPE), BOOLEAN(DataTypeType.BOOLEAN_DATA_TYPE), TO_ONE(
        DataTypeType.MANY_TO_ONE_RELATION_DATA_TYPE), TO_MANY(DataTypeType.ONE_TO_MANY_RELATION_DATA_TYPE), INTEGER(
        DataTypeType.INTEGER_DATA_TYPE), DOUBLE(DataTypeType.DOUBLE_DATA_TYPE);

    private final DataTypeEntity dataTypeEntity;

    DataTypeEnum(DataTypeEntity dataTypeEntity) {
        this.dataTypeEntity = dataTypeEntity;
    }

    @Override
    public String toString() {
        return this.dataTypeEntity.getName();
    }

    public EntityObject toEntity() {
        return this.dataTypeEntity;
    }

}
