package org.refact4j.evt;

/**
 * This class provides default implementations for the Event interface.
 *
 * @see Event
 */
public abstract class AbstractEvent<T> implements Event<T> {

    private final T source;

    public AbstractEvent(T source) {
        this.source = source;
    }

    /**
     * @see org.refact4j.evt.Event#getSource()
     */
    public T getSource() {
        return this.source;
    }
}
