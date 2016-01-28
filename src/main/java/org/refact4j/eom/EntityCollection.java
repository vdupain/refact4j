package org.refact4j.eom;

import org.refact4j.eom.model.Key;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * EntityCollection is a collection of EntityObjects.
 */
public interface EntityCollection extends Collection<EntityObject>, EntityFinder {

    default List<Key> getKeys() {
        return this.stream().map(e -> e.getKey()).collect(Collectors.toList());
    }


}
