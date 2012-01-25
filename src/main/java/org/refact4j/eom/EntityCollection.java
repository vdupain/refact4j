package org.refact4j.eom;

import org.refact4j.collection.Collection;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Key;

import java.util.List;

/**
 * EntityCollection is a collection of EntityObjects.
 */
public interface EntityCollection extends Collection<EntityObject, Key, EntityDescriptor>, EntityFinder {

    List<Key> getKeys();
}
