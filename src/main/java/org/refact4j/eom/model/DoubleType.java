package org.refact4j.eom.model;

public interface DoubleType extends DataType {

    interface DoubleTypeVisitor {
        void visitDoubleType(DoubleType doubleType);
    }
}
