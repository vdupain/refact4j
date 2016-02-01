package org.refact4j.eom;

import org.refact4j.collection.ChangeSetDelta;
import org.refact4j.evt.EventListener;
import org.refact4j.evt.EventListenerList;


public class EntityObjectEventSupport {

    private final EventListenerList<EntityObjectListener, EntityObjectEvent> listenerList = new EventListenerList<>();
    private final EntityObject entityObject;
    private EntityObjectEvent event = null;

    public EntityObjectEventSupport(EntityObject entityObject) {
        this.entityObject = entityObject;
    }

    public void registerListener(EntityObjectListener listener) {
        listenerList.add(listener);
    }

    public void unregisterListener(EntityObjectListener listener) {
        listenerList.remove(listener);
    }

    // Notify all listeners that have registered interest for
    // notification on this event type.
    public void fireEntityObjectChange(ChangeSetDelta<EntityObject> delta) {
        EventListener[] listeners = listenerList.getListenerList();
        for (EventListener listener : listeners) {
            if (event == null)
                event = new EntityObjectEvent(this.entityObject);
            ((EntityObjectListener) listener).notifyEntityObjectChange(event, delta);
        }
    }


}