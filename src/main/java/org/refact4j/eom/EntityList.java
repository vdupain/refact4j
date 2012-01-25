package org.refact4j.eom;

import org.refact4j.collection.List;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Key;

/**
 * EntityList is an ordered collection of EntityObject.
 */
public interface EntityList extends List<EntityObject, Key, EntityDescriptor>, EntityCollection {

}
