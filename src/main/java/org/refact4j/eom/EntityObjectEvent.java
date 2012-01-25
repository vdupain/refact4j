package org.refact4j.eom;

import org.refact4j.evt.AbstractEvent;

public class EntityObjectEvent extends AbstractEvent<EntityObject> {

    public EntityObjectEvent(EntityObject source) {
        super(source);
    }
}