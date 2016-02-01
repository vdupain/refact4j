package org.refact4j.collection;

import org.refact4j.evt.AbstractEvent;

class ChangeSetEvent<T> extends AbstractEvent<T> {

    public ChangeSetEvent(T source) {
        super(source);
    }
}
