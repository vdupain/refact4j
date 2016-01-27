package org.refact4j.collection.impl;

import org.refact4j.collection.ChangeSet;
import org.refact4j.collection.ChangeSetDelta;
import org.refact4j.collection.ChangeSetListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeSetImpl<T> implements ChangeSet<T> {

    private java.util.List<T> created = new ArrayList<T>();
    private java.util.List<T> deleted = new ArrayList<T>();
    private ChangeSetSupport changeSetSupport;
    private ChangeSetListener listener = new DefaultChangeSetListener<T>(this);
    private Map<T, List<ChangeSetDelta<T>>> deltas = new HashMap<T, List<ChangeSetDelta<T>>>();
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
        return new ArrayList<T>(this.deltas.keySet());
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
            deltas.put(t, d = new ArrayList<ChangeSetDelta<T>>());
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
