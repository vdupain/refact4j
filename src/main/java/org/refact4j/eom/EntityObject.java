package org.refact4j.eom;

import org.refact4j.core.Identifiable;
import org.refact4j.eom.model.*;
import org.refact4j.evt.Listenable;
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
        Identifiable<Key>, Listenable<EntityObjectListener> {

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
    Key getKey();

    /**
     * Retrieves the value of the field named fieldName.
     *
     * @param fieldName Identifies the field to retrieve.
     * @return The value of the field identified by fieldName.
     */
    Object get(String fieldName);

    /**
     * Retrieves the value of the field.
     *
     * @param field Identifies the field to retrieve.
     * @return The value of the field identified by the field.
     */
    Object get(Field field);

    /**
     * Retrieves the integer value of the typed integer field.
     *
     * @param integerField Identifies the integer field to retrieve.
     * @return The integer value of the field identified by the field.
     */
    Integer get(IntegerField integerField);

    /**
     * Retrieves the double value of the typed double field.
     *
     * @param doubleField Identifies the double field to retrieve.
     * @return The double value of the field identified by the field.
     */
    Double get(DoubleField doubleField);

    /**
     * Retrieves the string value of the typed string field.
     *
     * @param stringField Identifies the string field to retrieve.
     * @return The string value of the field identified by the field.
     */
    String get(StringField stringField);

    /**
     * Retrieves the date value of the typed date field.
     *
     * @param dateField Identifies the date field to retrieve.
     * @return The date value of the field identified by the field.
     */
    Date get(DateField dateField);

    /**
     * Retrieves the boolean value of the typed boolean field.
     *
     * @param booleanField Identifies the boolean field to retrieve.
     * @return The boolean value of the field identified by the field.
     */
    Boolean get(BooleanField booleanField);

    /**
     * Retrieves the value of the typed many-to-one field.
     *
     * @param manyToOneRelationField Identifies the field to retrieve.
     * @return The value of the field identified by the field.
     */
    Key get(ManyToOneRelationField manyToOneRelationField);

    /**
     * Retrieves the value of the typed one-to-one field.
     *
     * @param oneToOneRelationField Identifies the field to retrieve.
     * @return The value of the field identified by the field.
     */
    Key get(OneToOneRelationField oneToOneRelationField);

    /**
     * Sets the integer value for the field identified by integerField to
     * integerValue.
     *
     * @param integerField Identifies the field to change.
     * @param integerValue The new integer value for the integer field.
     * @return the current EntityObject instance.
     */
    EntityObject set(IntegerField integerField, Integer integerValue);

    /**
     * Sets the double value for the field identified by doubleField to
     * doubleValue.
     *
     * @param doubleField Identifies the field to change.
     * @param doubleValue The new double value for the double field.
     * @return the current EntityObject instance.
     */
    EntityObject set(DoubleField doubleField, Double doubleValue);

    /**
     * Sets the string value for the field identified by stringField to
     * stringValue.
     *
     * @param stringField Identifies the field to change.
     * @param stringValue The new string value for the string field.
     * @return the current EntityObject instance.
     */
    EntityObject set(StringField stringField, String stringValue);

    /**
     * Sets the date value for the field identified by dateField to dateValue.
     *
     * @param dateField Identifies the field to change.
     * @param dateValue The new date value for the date field.
     * @return the current EntityObject instance.
     */
    EntityObject set(DateField dateField, Date dateValue);

    /**
     * Sets the boolean value for the field identified by booleanField to
     * booleanValue.
     *
     * @param booleanField Identifies the field to change.
     * @param booleanValue The new boolean value for the date field.
     * @return the current EntityObject instance.
     */
    EntityObject set(BooleanField booleanField, Boolean booleanValue);

    /**
     * Sets the key value for the field identified by manyToOneRelationField to key.
     *
     * @param manyToOneRelationField Identifies the field to change.
     * @param key                    The new key value for the to-one field.
     * @return the current EntityObject instance.
     */
    EntityObject set(ManyToOneRelationField manyToOneRelationField, Key key);

    /**
     * Sets the key value for the field identified by oneToOneRelationField to key.
     *
     * @param oneToOneRelationField Identifies the field to change.
     * @param key                   The new key value for the to-one field.
     * @return the current EntityObject instance.
     */
    EntityObject set(OneToOneRelationField oneToOneRelationField, Key key);

    /**
     * Sets the EntityObject value for the field identified by
     * manyToOneRelationField to entityObject.
     *
     * @param manyToOneRelationField Identifies the field to change.
     * @param entityObject           The new EntityObject value for the to-one field.
     * @return the current EntityObject instance.
     */
    EntityObject set(ManyToOneRelationField manyToOneRelationField, EntityObject entityObject);

    /**
     * Sets the EntityObject value for the field identified by
     * oneToOneRelationField to entityObject.
     *
     * @param oneToOneRelationField Identifies the field to change.
     * @param entityObject          The new EntityObject value for the to-one field.
     * @return the current EntityObject instance.
     */
    EntityObject set(OneToOneRelationField oneToOneRelationField, EntityObject entityObject);

    /**
     * Sets the value for the field identified by fieldName to value.
     *
     * @param fieldName Identifies the field to change.
     * @param value     The new value for the field.
     * @return the current EntityObject instance.
     */
    EntityObject set(String fieldName, Object value);

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
    void checkConstraint();

    /**
     * Checks all values of the EntityObject (checks for nullable value, type of
     * the value to set...).
     */
    void checkValues();

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
