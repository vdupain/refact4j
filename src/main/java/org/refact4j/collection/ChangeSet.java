package org.refact4j.collection;

public interface ChangeSet<T> {

    void startRecordChanges();

    void stopRecordChanges();

    java.util.List<T> getUpdatedObjects();

    java.util.List<T> getCreatedObjects();

    java.util.List<T> getDeletedObjects();

    java.util.List<ChangeSetDelta<T>> getDeltas(T t);

    boolean isChangesRecording();
}
