package org.refact4j.eom.model;

/**
 * A OneToOneRelationField represents a one-to-one relationship field.
 */

public interface OneToOneRelationField extends RelationField, OneToOneRelationType {

    interface OneToOneRelationFieldVisitor {
        void visitOneToOneRelationField(OneToOneRelationField oneToOneRelationField);
    }

}