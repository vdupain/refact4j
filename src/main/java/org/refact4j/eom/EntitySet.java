package org.refact4j.eom;

import org.refact4j.collection.Set;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;

/**
 * EntitySet is a collection of EntityObject that contains no duplicate
 * elements.
 */

public interface EntitySet extends Set<EntityObject, Key, EntityDescriptor>, EntityCollection {

    EntityList getEntities(EntityDescriptor entityDescriptor, Field field, Object value);

    void delete(EntityObject entityObject);

    void create(EntityObject entityObject);
}
