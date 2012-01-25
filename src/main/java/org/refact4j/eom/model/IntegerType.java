package org.refact4j.eom.model;

public interface IntegerType extends DataType {

    interface IntegerTypeVisitor {
        void visitIntegerType(IntegerType integerType);
    }
}
