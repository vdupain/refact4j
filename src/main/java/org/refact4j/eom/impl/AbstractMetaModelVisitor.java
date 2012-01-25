package org.refact4j.eom.impl;

import org.refact4j.eom.model.*;
import org.refact4j.visitor.Visitable;

public abstract class AbstractMetaModelVisitor implements MetaModelVisitor {
    EntityDescriptorRepository entityDescriptorRepository;

    public void visitEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository) {
        this.entityDescriptorRepository = entityDescriptorRepository;
        for (EntityDescriptor entityDescriptor : entityDescriptorRepository) {
            entityDescriptor.accept(this);
            for (Field field : entityDescriptor.getFields()) {
                field.accept(this);
            }
        }
    }

    public void visit(Visitable visitable) {
    }

    public void visitIntegerField(IntegerField integerField) {
        this.visitField(integerField);
    }

    public void visitDoubleField(DoubleField doubleField) {
        this.visitField(doubleField);
    }

    public void visitStringField(StringField stringField) {
        this.visitField(stringField);
    }

    public void visitDateField(DateField dateField) {
        this.visitField(dateField);
    }

    public void visitBooleanField(BooleanField booleanField) {
        this.visitField(booleanField);
    }

    public void visitManyToOneRelationField(ManyToOneRelationField manyToOneRelationField) {
        this.visitField(manyToOneRelationField);
    }

    public void visitOneToManyRelationField(OneToManyRelationField oneToManyRelationField) {
        this.visitField(oneToManyRelationField);
    }

    public void visitOneToOneRelationField(OneToOneRelationField oneToOneRelationField) {
        this.visitField(oneToOneRelationField);
    }

}
