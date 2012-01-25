package org.refact4j.eom.model;

/**
 * A RelationField represents a relationship property. Sub interfaces of
 * RelationField are ToOneRelationField, ToManyRelationField.
 */
public interface RelationField extends Field {

    /**
     * Returns the relationship's EntityDescriptor.
     *
     * @return The relationship's EntityDescriptor.
     */
    EntityDescriptor getTargetEntityDescriptor();

    /**
     * Searches the relationship's destination EntityDescriptor for a
     * relationship joining on the same keys. Returns the inverse relationship
     * if one is found, null otherwise.
     *
     * @return The inverse relationship if one is found, null otherwise.
     */
    RelationField getInverseRelationField();

}
