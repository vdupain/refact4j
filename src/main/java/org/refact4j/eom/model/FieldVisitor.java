package org.refact4j.eom.model;

import org.refact4j.eom.model.BooleanField.BooleanFieldVisitor;
import org.refact4j.eom.model.DateField.DateFieldVisitor;
import org.refact4j.eom.model.DoubleField.DoubleFieldVisitor;
import org.refact4j.eom.model.IntegerField.IntegerFieldVisitor;
import org.refact4j.eom.model.OneToManyRelationField.ToManyRelationFieldVisitor;
import org.refact4j.eom.model.StringField.StringFieldVisitor;

public interface FieldVisitor extends IntegerFieldVisitor, DoubleFieldVisitor, StringFieldVisitor, DateFieldVisitor,
        BooleanFieldVisitor, ManyToOneRelationField.ManyToOneRelationFieldVisitor, ToManyRelationFieldVisitor, OneToOneRelationField.OneToOneRelationFieldVisitor {

    void visitField(Field field);

}
