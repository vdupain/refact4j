package org.refact4j.eom.model;

public interface OneToOneRelationType extends RelationType {

    interface OneToOneRelationTypeVisitor {
        void visitOneToOneRelationType(OneToOneRelationType oneToOneRelationType);
    }
}