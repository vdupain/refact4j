package org.refact4j.eom.model;

import org.refact4j.eom.ToEntity;
import org.refact4j.eom.model.impl.Stringifiers;
import org.refact4j.xml.ToXmlString;

import java.io.Externalizable;

/**
 * The Field interface is an interface that embodies the notion of an object
 * that is the property of an EntityDescriptor. A Field represents a property, a
 * field in an Object or a column in a database. Sub interfaces of Field are
 * DataField (IntegerField, DoubleField, BooleanField, StringField, DateField)
 * and RelationField (ToOneRelationField, ToManyRelationField).
 */
public interface Field extends DataType, ToEntity, ToXmlString, Externalizable {

    /**
     * Returns the internal name of the field. The internal name must be unique
     * within its EntityDescriptor.
     *
     * @return The field's internal name.
     */
    String getName();

    /**
     * Returns the full name of the field. The full name is the concatenation of
     * the EntityDescriptor's name and the field's name.
     *
     * @return The field's full name.
     */
    default String getFullName() {
        return getEntityDescriptor().getName() + "." + getName();
    }


    /**
     * Returns the pretty name of the field.
     *
     * @return The field's pretty name.
     */
    String getPrettyName();

    /**
     * Returns the DataType of the field.
     *
     * @return The DataType of the field.
     */
    DataType getDataType();

    /**
     * Returns the EntityDescriptor that owns the field.
     *
     * @return The EntityDescriptor that owns the field.
     */
    EntityDescriptor getEntityDescriptor();

    /**
     * Returns the default value of the field.
     *
     * @return The default value of the field.
     */
    Object getDefaultValue();

    /**
     * Returns true if the field is nullable.
     *
     * @return true if the field is nullable.
     */
    boolean isNullable();

    void accept(FieldVisitor fieldVisitor);

    void checkValue(Object value);

    default String toXmlString() {
        return Stringifiers.FIELD_XML.stringify(this);
    }

}
