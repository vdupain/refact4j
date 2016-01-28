package org.refact4j.collection;

public class DefaultChangeSetListener<T> implements ChangeSetListener<T> {
    private ChangeSet<T> changeSet;

    public DefaultChangeSetListener(ChangeSet<T> changeSet) {
        this.changeSet = changeSet;
    }

    public void notifyChangeSetUpdate(ChangeSetEvent<T> event, T t, ChangeSetDelta changeSetDelta) {

    }

    public void notifyChangeSetDelete(ChangeSetEvent event, T t) {
        this.changeSet.getDeletedObjects().add(t);
    }

    public void notifyChangeSetCreate(ChangeSetEvent event, T t) {
        this.changeSet.getCreatedObjects().add(t);
    }

    public void notifyEvent(ChangeSetEvent<T> event) {
        // noop
    }
}
