package org.refact4j.eom.metamodel;

import org.refact4j.eom.String2ValueFieldConverter;
import org.refact4j.eom.Value2StringFieldConverter;
import org.refact4j.eom.model.*;

public class DataTypeConverter implements DataTypeVisitor {
    private final String2ValueFieldConverter string2ValueConverter = new String2ValueFieldConverter();

    private final Value2StringFieldConverter value2StringConverter = new Value2StringFieldConverter();

    public DataTypeConverter() {
    }

    public void visitDataType(DataType dataType) {
        string2ValueConverter.visitField(null);
        value2StringConverter.visitField(null);
    }

    public void visitIntegerType(IntegerType integerType) {
        string2ValueConverter.visitIntegerField(null);
        value2StringConverter.visitIntegerField(null);
    }

    public void visitStringType(StringType stringType) {
        string2ValueConverter.visitStringField(null);
        value2StringConverter.visitStringField(null);
    }

    public void visitDoubleType(DoubleType doubleType) {
        string2ValueConverter.visitDoubleField(null);
        value2StringConverter.visitDoubleField(null);
    }

    public void visitDateType(DateType dateType) {
        string2ValueConverter.visitDateField(null);
        value2StringConverter.visitDateField(null);
    }

    public void visitBooleanType(BooleanType booleanType) {
        string2ValueConverter.visitBooleanField(null);
        value2StringConverter.visitBooleanField(null);
    }

    public void visitManyToOneRelationType(ManyToOneRelationType manyToOneRelationType) {
    }

    public void visitOneToManyRelationType(OneToManyRelationType oneToManyRelationType) {
    }

    public void visitOneToOneRelationType(OneToOneRelationType oneToOneRelationType) {
    }

    public String2ValueFieldConverter getString2ValueConverter() {
        return this.string2ValueConverter;
    }

    public Value2StringFieldConverter getValue2StringConverter() {
        return this.value2StringConverter;
    }

}
