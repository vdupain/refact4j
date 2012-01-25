package org.refact4j.eom.model;

public interface ManyToOneRelationType extends RelationType {

    interface ManyToOneRelationTypeVisitor {
        void visitManyToOneRelationType(ManyToOneRelationType manyToOneRelationType);
    }
}
