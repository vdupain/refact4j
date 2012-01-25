package org.refact4j.eom.model;

public interface StringType extends DataType {

    interface StringTypeVisitor {
        void visitStringType(StringType stringType);
    }
}
