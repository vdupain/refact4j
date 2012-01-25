package org.refact4j.eom.model;

public interface OneToManyRelationType extends RelationType {

    interface OneToManyRelationTypeVisitor {
        void visitOneToManyRelationType(OneToManyRelationType oneToManyRelationType);
    }
}
