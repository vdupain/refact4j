package org.refact4j.eom;

import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;

import java.util.Collection;

/**
 * EntitySet is a collection of EntityObject that contains no duplicate
 * elements.
 */

public interface EntitySet extends java.util.Set<EntityObject>, EntityFinder {

    EntityList getEntities(EntityDescriptor entityDescriptor, Field field, Object value);

}
