package org.refact4j.collection;

import java.util.List;

public interface ChangeSet<T> {

    void startRecordChanges();

    void stopRecordChanges();

    List<T> getUpdatedObjects();

    List<T> getCreatedObjects();

    List<T> getDeletedObjects();

    List<ChangeSetDelta<T>> getDeltas(T t);
}
