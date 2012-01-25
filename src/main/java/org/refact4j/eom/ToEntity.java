package org.refact4j.eom;

/**
 * ToEntity interface is an interface that specifies toEntity method. This
 * interface should be implemented by all objects that can be converted to a
 * EntityObject.
 */
public interface ToEntity {

    /**
     * Converts object to a EntityObject.
     *
     * @return The EntityObject.
     */
    EntityObject toEntity();
}
