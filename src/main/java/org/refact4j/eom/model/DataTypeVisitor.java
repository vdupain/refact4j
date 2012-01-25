package org.refact4j.eom.model;

import org.refact4j.eom.model.BooleanType.BooleanTypeVisitor;
import org.refact4j.eom.model.DateType.DateTypeVisitor;
import org.refact4j.eom.model.DoubleType.DoubleTypeVisitor;
import org.refact4j.eom.model.IntegerType.IntegerTypeVisitor;
import org.refact4j.eom.model.OneToManyRelationType.OneToManyRelationTypeVisitor;
import org.refact4j.eom.model.StringType.StringTypeVisitor;

public interface DataTypeVisitor extends IntegerTypeVisitor, StringTypeVisitor, DoubleTypeVisitor, DateTypeVisitor,
        BooleanTypeVisitor, ManyToOneRelationType.ManyToOneRelationTypeVisitor, OneToManyRelationTypeVisitor,
        OneToOneRelationType.OneToOneRelationTypeVisitor {
    void visitDataType(DataType dataType);
}
