package org.refact4j.eom.model;

public interface BooleanType extends DataType {

    interface BooleanTypeVisitor {
        void visitBooleanType(BooleanType booleanType);
    }
}
