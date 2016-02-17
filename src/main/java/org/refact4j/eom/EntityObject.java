package org.refact4j.eom;

import org.refact4j.eom.model.*;
import org.refact4j.evt.Listenable;
import org.refact4j.expr.Expression;
import org.refact4j.xml.ToXML;
import org.refact4j.xml.ToXmlString;

import java.io.Externalizable;
import java.io.Serializable;
import java.util.Date;

/**
 * The EntityObject interface identifies basic generic object behavior, defining
 * standard methods for supporting common to all generic objects. Among these
 * are methods for setting or retrieving property values and performing check of
 * state. You should never implement the EntityObject interface as a default
 * implementation already exists.
 */

public interface EntityObject extends Cloneable, ToXML, ToXmlString, Serializable, Externalizable, Iterable<Field>,
        Listenable<EntityObjectListener> {

    /**
     * Returns the EntityDescriptor associated with the EntityObject.
     *
     * @return The EntityDescriptor associated with the EntityObject.
     */
    EntityDescriptor getEntityDescriptor();

    /**
     * Returns the EntityObject's key that identify the EntityObject.
     *
     * @return The EntityObject's key.
     */
    default Key getKey() {
        KeyBuilder keyBuilder = KeyBuilder.init(this.getEntityDescriptor());
        this.getEntityDescriptor().getKeyFields().stream().forEach(field -> keyBuilder.set(field, get(field)));
        return keyBuilder.get();
    }

    /**
     * Retrieves the value of the field named fieldName.
     *
     * @param fieldName Identifies the field to retrieve.
     * @return The value of the field identified by fieldName.
     */
    default Object get(String fieldName) {
        return this.getFieldValue(this.getEntityDescriptor().getField(fieldName));
    }


    /**
     * Retrieves the value of the field.
     *
     * @param field Identifies the field to retrieve.
     * @return The value of the field identified by fieldName.
     */
    Object getFieldValue(Field field);

    /**
     * Retrieves the value of the field.
     *
     * @param field Identifies the field to retrieve.
     * @return The value of the field identified by the field.
     */
    default Object get(Field field) {
        return this.getFieldValue(field);
    }


    /**
     * Retrieves the integer value of the typed integer field.
     *
     * @param integerField Identifies the integer field to retrieve.
     * @return The integer value of the field identified by the field.
     */
    default Integer get(IntegerField integerField) {
        return (Integer) this.getFieldValue(integerField);
    }


    /**
     * Retrieves the double value of the typed double field.
     *
     * @param doubleField Identifies the double field to retrieve.
     * @return The double value of the field identified by the field.
     */
    default Double get(DoubleField doubleField) {
        return (Double) this.getFieldValue(doubleField);
    }

    /**
     * Retrieves the string value of the typed string field.
     *
     * @param stringField Identifies the string field to retrieve.
     * @return The string value of the field identified by the field.
     */
    default String get(StringField stringField) {
        return (String) this.getFieldValue(stringField);
    }


    /**
     * Retrieves the date value of the typed date field.
     *
     * @param dateField Identifies the date field to retrieve.
     * @return The date value of the field identified by the field.
     */
    default Date get(DateField dateField) {
        return (Date) this.getFieldValue(dateField);
    }


    /**
     * Retrieves the boolean value of the typed boolean field.
     *
     * @param booleanField Identifies the boolean field to retrieve.
     * @return The boolean value of the field identified by the field.
     */
    default Boolean get(BooleanField booleanField) {
        return (Boolean) this.getFieldValue(booleanField);
    }


    /**
     * Retrieves the value of the typed many-to-one field.
     *
     * @param manyToOneRelationField Identifies the field to retrieve.
     * @return The value of the field identified by the field.
     */
    default Key get(ManyToOneRelationField manyToOneRelationField) {
        return (Key) this.getFieldValue(manyToOneRelationField);
    }


    /**
     * Retrieves the value of the typed one-to-one field.
     *
     * @param oneToOneRelationField Identifies the field to retrieve.
     * @return The value of the field identified by the field.
     */
    default Key get(OneToOneRelationField oneToOneRelationField) {
        return (Key) this.getFieldValue(oneToOneRelationField);
    }


    /**
     * Sets the integer value for the field identified by integerField to
     * integerValue.
     *
     * @param integerField Identifies the field to change.
     * @param integerValue The new integer value for the integer field.
     * @return the current EntityObject instance.
     */
    default EntityObject set(IntegerField integerField, Integer integerValue) {
        this.set((Field) integerField, integerValue);
        return this;
    }

    /**
     * Sets the double value for the field identified by doubleField to
     * doubleValue.
     *
     * @param doubleField Identifies the field to change.
     * @param doubleValue The new double value for the double field.
     * @return the current EntityObject instance.
     */
    default EntityObject set(DoubleField doubleField, Double doubleValue) {
        this.set((Field) doubleField, doubleValue);
        return this;
    }

    /**
     * Sets the string value for the field identified by stringField to
     * stringValue.
     *
     * @param stringField Identifies the field to change.
     * @param stringValue The new string value for the string field.
     * @return the current EntityObject instance.
     */
    default EntityObject set(StringField stringField, String stringValue) {
        this.set((Field) stringField, stringValue);
        return this;
    }


    /**
     * Sets the date value for the field identified by dateField to dateValue.
     *
     * @param dateField Identifies the field to change.
     * @param dateValue The new date value for the date field.
     * @return the current EntityObject instance.
     */
    default EntityObject set(DateField dateField, Date dateValue) {
        this.set((Field) dateField, dateValue);
        return this;
    }

    /**
     * Sets the boolean value for the field identified by booleanField to
     * booleanValue.
     *
     * @param booleanField Identifies the field to change.
     * @param booleanValue The new boolean value for the date field.
     * @return the current EntityObject instance.
     */
    default EntityObject set(BooleanField booleanField, Boolean booleanValue) {
        this.set((Field) booleanField, booleanValue);
        return this;
    }

    /**
     * Sets the key value for the field identified by manyToOneRelationField to key.
     *
     * @param manyToOneRelationField Identifies the field to change.
     * @param key                    The new key value for the to-one field.
     * @return the current EntityObject instance.
     */
    default EntityObject set(ManyToOneRelationField manyToOneRelationField, Key key) {
        this.set((Field) manyToOneRelationField, key);
        return this;
    }

    /**
     * Sets the key value for the field identified by oneToOneRelationField to key.
     *
     * @param oneToOneRelationField Identifies the field to change.
     * @param key                   The new key value for the to-one field.
     * @return the current EntityObject instance.
     */
    default EntityObject set(OneToOneRelationField oneToOneRelationField, Key key) {
        this.set((Field) oneToOneRelationField, key);
        return this;
    }

    /**
     * Sets the EntityObject value for the field identified by
     * manyToOneRelationField to entityObject.
     *
     * @param manyToOneRelationField Identifies the field to change.
     * @param entityObject           The new EntityObject value for the to-one field.
     * @return the current EntityObject instance.
     */
    default EntityObject set(ManyToOneRelationField manyToOneRelationField, EntityObject entityObject) {
        this.set((Field) manyToOneRelationField, entityObject != null ? entityObject.getKey() : null);
        return this;
    }

    /**
     * Sets the EntityObject value for the field identified by
     * oneToOneRelationField to entityObject.
     *
     * @param oneToOneRelationField Identifies the field to change.
     * @param entityObject          The new EntityObject value for the to-one field.
     * @return the current EntityObject instance.
     */
    default EntityObject set(OneToOneRelationField oneToOneRelationField, EntityObject entityObject) {
        this.set((Field) oneToOneRelationField, entityObject != null ? entityObject.getKey() : null);
        return this;
    }

    /**
     * Sets the value for the field identified by fieldName to value.
     *
     * @param fieldName Identifies the field to change.
     * @param value     The new value for the field.
     * @return the current EntityObject instance.
     */
    default EntityObject set(String fieldName, Object value) {
        Field field = this.getEntityDescriptor().getField(fieldName);
        if (field == null) {
            throw new RuntimeException("Entity '" + this.getEntityDescriptor().getName() + "' does not contain field '"
                    + fieldName + "'");
        }
        this.set(field, value);
        return this;
    }

    /**
     * Sets the value for the field identified by field to value.
     *
     * @param field Identifies the field to change.
     * @param value The new value for the field.
     * @return the current EntityObject instance.
     */
    EntityObject set(Field field, Object value);

    /**
     * Checks constraint on EntityObject.
     */
    default void checkConstraint() {
        Expression constraint = this.getEntityDescriptor().getConstraintExpression();
        if (constraint != null && !constraint.test(this)) {
            throw new RuntimeException("Constraint " + constraint + " failed: " + constraint.getPropertyName() + "="
                    + this.get(constraint.getPropertyName()));
        }
    }

    /**
     * Checks all values of the EntityObject (checks for nullable value, type of
     * the value to set...).
     */
    default void checkValues() {
        getEntityDescriptor().getFields()
                .stream().forEach(field -> field.checkValue(get(field)));
    }


    /**
     * Accepts a EntityObject's visitor (Visitor Design Pattern).
     *
     * @param visitor A visitor
     */
    void accept(EntityObjectVisitor visitor);

    /**
     * Creates and returns a copy of this EntityObject. The general intent is
     * that, for any EntityObject, the assertions will be: EntityObject
     * entityObject = ...; assertTrue(entityObject.clone() != entityObject);
     * assertTrue(entityObject.clone().getClass() == entityObject.getClass());
     * assertTrue(entityObject.clone().equals(entityObject));
     *
     * @return A clone of this instance.
     */
    Object clone() throws CloneNotSupportedException;

    interface EntityObjectVisitor {
        void visitEntityObject(EntityObject entityObject);
    }

}
