package org.refact4j.evt;

/**
 * An interface that all event listener interfaces must extend.
 *
 * @see Event
 */

public interface EventListener<T extends Event<?>> extends java.util.EventListener {

    /**
     * Invoked when an event occurs.
     *
     * @param event event
     */
    void notifyEvent(T event);
}
