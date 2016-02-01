package org.refact4j.collection;

import org.refact4j.evt.EventListener;

interface ChangeSetListener<T> extends EventListener<ChangeSetEvent<T>> {

    void notifyChangeSetUpdate(ChangeSetEvent<T> event, T t, ChangeSetDelta changeSetDelta);

    void notifyChangeSetDelete(ChangeSetEvent event, T t);

    void notifyChangeSetCreate(ChangeSetEvent event, T t);
}
