package org.refact4j.eom;

import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;

/**
 * EntitySet is a collection of EntityObject that contains no duplicate
 * elements.
 */

public interface EntitySet extends java.util.Set<EntityObject>, EntityCollection {

    EntityList getEntities(EntityDescriptor entityDescriptor, Field field, Object value);

}
