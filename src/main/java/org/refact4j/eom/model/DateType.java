package org.refact4j.eom.model;

public interface DateType extends DataType {

    interface DateTypeVisitor {
        void visitDateType(DateType dateType);
    }
}
