package org.refact4j.eom.impl;

import org.refact4j.collection.ChangeSetDelta;
import org.refact4j.collection.impl.ChangeSetImpl;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectEvent;
import org.refact4j.eom.EntityObjectListener;

public class ChangeSetEntityObjectListener implements EntityObjectListener {
	private ChangeSetImpl<EntityObject> changeSet;

	public ChangeSetEntityObjectListener(ChangeSetImpl<EntityObject> changeSet) {
		this.changeSet = changeSet;
	}
	
	public void notifyEntityObjectChange(EntityObjectEvent event, ChangeSetDelta<EntityObject> delta) {
		changeSet.addChangeSetDelta(delta.getSource(), delta);
	}

	public void notifyEvent(EntityObjectEvent event) {

	}

}
