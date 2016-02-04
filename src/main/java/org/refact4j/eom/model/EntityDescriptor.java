package org.refact4j.eom.model;

import org.refact4j.eom.EOMContext;
import org.refact4j.eom.EntityExpression;
import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.ToEntity;
import org.refact4j.expr.Expression;
import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;
import org.refact4j.xml.ToXmlString;

import java.io.Externalizable;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * An EntityDescriptor describes a EntityObject. An EntityDescriptor maintains a
 * group of attributes and relationships which are formally called fields. These
 * are represented by the Field interface. An EntityDescriptor is identify by it
 * name. EntityDescriptor objects are primarily use by the framework for mapping
 * Java beans or business objects in the application. You usually defines
 * EntityDescriptors in an EntityDescriptor Repository describing them in XML
 * format.
 */
public interface EntityDescriptor extends Property, ToXmlString, ToEntity, Serializable, Externalizable, Visitable {

    /**
     * Returns The name of the EntityDescriptor.
     *
     * @return the name of the EntityDescriptor.
     */
    String getName();

    /**
     * Returns a collection containing all of the EntityDescriptor's fields
     * (data and relationship).
     *
     * @return A collection of all the EntityDescriptor's fields.
     */
    Collection<Field> getFields();

    /**
     * Returns the field named fieldName, or null if no such field exists in the
     * EntityDescriptor.
     *
     * @param fieldName The name of a field.
     * @return The named field.
     */
    Field getField(String fieldName);

    /**
     * Returns the constraint expression of the EntityDescriptor.
     *
     * @return The constraint expression.
     */
    Expression getConstraintExpression();

    /**
     * Returns a list containing all of the EntityDescriptor's fields that
     * identifies the key.
     *
     * @return A list of all the EntityDescriptor's fields that identifies the
     * key.
     */
    List<Field> getKeyFields();

    /**
     * Returns a list containing all of the EntityDescriptor's data fields
     * (IntegerField, DoubleField, BooleanField, StringField, DateField).
     *
     * @return A list of all the EntityDescriptor's data fields.
     */
    List<DataField> getAttributeFields();

    /**
     * Returns a list containing all of the EntityDescriptor's relationship
     * fields (ToOneRelationField, ToManyRelationField).
     *
     * @return A list of all the EntityDescriptor's relationship fields.
     */
    List<RelationField> getRelationFields();

    /**
     * Returns true if this EntityDescriptor contains the specified field.
     *
     * @param field The field whose presence in the EntityDescriptor is to be
     *              tested.
     * @return true if the EntityDescriptor contains the specified field.
     */
    boolean containsField(Field field);

    /**
     * Returns the EntityDescriptor's stringifier.
     *
     * @return The EntityDescriptor's stringifier.
     */
    EntityStringifier getEntityStringifier();

    /**
     * Return the bean class for EntityObject/Java Bean mapping.
     *
     * @return The bean class.
     */
    Class<?> getBeanClass();

    /**
     * Return the context for the EntityObject.
     *
     * @return The context.
     */
    EOMContext getContext();

    interface EntityDescriptorVisitor extends Visitor {
        void visitEntityDescriptor(EntityDescriptor entityDescriptor);
    }

}
