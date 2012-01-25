package org.refact4j.collection;

import org.refact4j.evt.AbstractEvent;

public class ChangeSetEvent<T> extends AbstractEvent<T> {

    public ChangeSetEvent(T source) {
        super(source);
    }
}
