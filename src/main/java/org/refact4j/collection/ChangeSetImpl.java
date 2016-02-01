package org.refact4j.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeSetImpl<T> implements ChangeSet<T> {

    private final java.util.List<T> created = new ArrayList<>();
    private final java.util.List<T> deleted = new ArrayList<>();
    private final ChangeSetSupport changeSetSupport;
    private final ChangeSetListener listener = new DefaultChangeSetListener<>(this);
    private final Map<T, List<ChangeSetDelta<T>>> deltas = new HashMap<>();
    private boolean isChangesRecording;

    public ChangeSetImpl(ChangeSetSupport changeSetSupport) {
        this.changeSetSupport = changeSetSupport;
    }

    public void startRecordChanges() {
        this.clear();
        this.changeSetSupport.registerListener(listener);
        this.isChangesRecording = true;
    }

    public void stopRecordChanges() {
        this.changeSetSupport.unregisterListener(listener);
        this.isChangesRecording = false;
    }

    public List<T> getUpdatedObjects() {
        return new ArrayList<>(this.deltas.keySet());
    }

    public List<T> getDeletedObjects() {
        return this.deleted;
    }

    public List<T> getCreatedObjects() {
        return this.created;
    }

    public List<ChangeSetDelta<T>> getDeltas(T t) {
        return deltas.get(t);
    }

    public void addChangeSetDelta(T t, ChangeSetDelta<T> delta) {
        getDeltasByT(t).add(delta);
    }

    private List<ChangeSetDelta<T>> getDeltasByT(T t) {
        List<ChangeSetDelta<T>> d = deltas.get(t);
        if (d == null) {
            deltas.put(t, d = new ArrayList<>());
        }
        return d;
    }

    private void clear() {
        this.created.clear();
        this.deleted.clear();
        this.deltas.clear();
    }

    public boolean isChangesRecording() {
        return this.isChangesRecording;
    }


}
