package org.refact4j.collection;

import org.refact4j.evt.EventListener;
import org.refact4j.evt.EventListenerList;


class ChangeSetSupport<T> {

    private final EventListenerList<ChangeSetListener<T>, ChangeSetEvent<T>> listenerList = new EventListenerList<>();
    private ChangeSetEvent<T> event = null;

    public void registerListener(ChangeSetListener<T> listener) {
        listenerList.add(listener);
    }

    public void unregisterListener(ChangeSetListener<T> listener) {
        listenerList.remove(listener);
    }

    // Notify all listeners that have registered interest for
    // notification on this event type.
    public void fireChangeSetUpdate() {
        EventListener[] listeners = listenerList.getListenerList();
        for (EventListener listener : listeners) {
            if (event == null)
                event = new ChangeSetEvent<>(null);
            ((ChangeSetListener) listener).notifyChangeSetUpdate(event, null, null);
        }
    }

    public void fireChangeSetDelete(T t) {
        EventListener[] listeners = listenerList.getListenerList();
        for (EventListener listener : listeners) {
            if (event == null)
                event = new ChangeSetEvent(null);
            ((ChangeSetListener) listener).notifyChangeSetDelete(event, t);
        }
    }

    public void fireChangeSetCreate(T t) {
        EventListener[] listeners = listenerList.getListenerList();
        for (EventListener listener : listeners) {
            if (event == null)
                event = new ChangeSetEvent(null);
            ((ChangeSetListener) listener).notifyChangeSetCreate(event, t);
        }
    }

}
