package org.refact4j.eom;

import org.refact4j.eom.model.Key;

import java.util.Collection;
import java.util.List;

/**
 * EntityCollection is a collection of EntityObjects.
 */
public interface EntityCollection extends Collection<EntityObject>, EntityFinder {

    List<Key> getKeys();
}
