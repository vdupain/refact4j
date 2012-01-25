package org.refact4j.eom;

import org.refact4j.collection.ChangeSetDelta;
import org.refact4j.eom.impl.EntityObjectChangeSetDeltaImpl;
import org.refact4j.eom.model.Field;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EntityObjectChangeSetDeltaPropertyChangeListener implements PropertyChangeListener {
    private EntityObjectEventSupport entityObjectEventSupport;


    public EntityObjectChangeSetDeltaPropertyChangeListener(EntityObjectEventSupport entityObjectEventSupport) {
        this.entityObjectEventSupport = entityObjectEventSupport;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        EntityObject source = (EntityObject) evt.getSource();
        Field field = source.getEntityDescriptor().getField(evt.getPropertyName());
        ChangeSetDelta<EntityObject> delta = new EntityObjectChangeSetDeltaImpl(source, field, evt.getOldValue(), evt.getNewValue());
        entityObjectEventSupport.fireEntityObjectChange(delta);
    }
}
