package org.refact4j.eom.model;

/**
 * A ManyToOneRelationField represents a many-to-one relationship field.
 */

public interface ManyToOneRelationField extends RelationField, ManyToOneRelationType {

    interface ManyToOneRelationFieldVisitor {
        void visitManyToOneRelationField(ManyToOneRelationField manyToOneRelationField);
    }

}
