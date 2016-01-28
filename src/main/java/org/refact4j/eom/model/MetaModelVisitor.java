package org.refact4j.eom.model;

import org.refact4j.eom.model.EntityDescriptor.EntityDescriptorVisitor;
import org.refact4j.eom.model.EntityDescriptorRepository.EntityDescriptorRepositoryVisitor;
import org.refact4j.visitor.Visitable;

public interface MetaModelVisitor extends EntityDescriptorRepositoryVisitor, EntityDescriptorVisitor, FieldVisitor {

    default void visit(Visitable visitable) {
    }

    default void visitIntegerField(IntegerField integerField) {
        this.visitField(integerField);
    }

    default void visitDoubleField(DoubleField doubleField) {
        this.visitField(doubleField);
    }

    default void visitStringField(StringField stringField) {
        this.visitField(stringField);
    }

    default void visitDateField(DateField dateField) {
        this.visitField(dateField);
    }

    default void visitBooleanField(BooleanField booleanField) {
        this.visitField(booleanField);
    }

    default void visitManyToOneRelationField(ManyToOneRelationField manyToOneRelationField) {
        this.visitField(manyToOneRelationField);
    }

    default void visitOneToManyRelationField(OneToManyRelationField oneToManyRelationField) {
        this.visitField(oneToManyRelationField);
    }

    default void visitOneToOneRelationField(OneToOneRelationField oneToOneRelationField) {
        this.visitField(oneToOneRelationField);
    }


}
