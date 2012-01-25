package org.refact4j.eom.model;

/**
 * A OneToManyRelationField represents a one-to-many relationship field.
 */

public interface OneToManyRelationField extends RelationField, OneToManyRelationType {

    interface ToManyRelationFieldVisitor {
        void visitOneToManyRelationField(OneToManyRelationField oneToManyRelationField);
    }

}
