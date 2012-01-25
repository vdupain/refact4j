package org.refact4j.eom.model;

/**
 * DataType is a tagging interface that is used to identify the type of a data
 * Field.
 */
public interface DataType {
    void accept(DataTypeVisitor dataTypeVisitor);

}
