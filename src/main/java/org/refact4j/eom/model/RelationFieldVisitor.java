package org.refact4j.eom.model;

import org.refact4j.eom.model.OneToManyRelationField.ToManyRelationFieldVisitor;

public interface RelationFieldVisitor extends ManyToOneRelationField.ManyToOneRelationFieldVisitor, ToManyRelationFieldVisitor {

}
