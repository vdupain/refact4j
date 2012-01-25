package org.refact4j.eom;

import org.refact4j.collection.ChangeSetDelta;
import org.refact4j.evt.EventListener;

public interface EntityObjectListener extends EventListener<EntityObjectEvent> {

    void notifyEntityObjectChange(EntityObjectEvent event, ChangeSetDelta<EntityObject> delta);
}