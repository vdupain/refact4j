package org.refact4j.evt;

/**
 * The root interface from which all event state objects shall be derived. All
 * Events are constructed with a reference to the object, the "source", that is
 * logically deemed to be the object upon which the Event in question initially
 * occurred upon.
 */

public interface Event<T> {

    /**
     * The object on which the Event initially occurred.
     *
     * @return The object on which the Event initially occurred.
     */
    T getSource();
}
