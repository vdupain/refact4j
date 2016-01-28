package org.refact4j.evt;

import org.junit.Test;
import org.refact4j.evt.EventManager.NotifyEventFunctor;

import static org.junit.Assert.assertEquals;


public class EventTest {

    @Test
    public void testEventListener() {
        final EventLogger eventLogger = new EventLogger();
        DummyListener listener1 = new DummyListener() {
            public void notifyDummyEvent(DummyEvent event) {
                assertEquals(EventTest.this, event.getSource());
                eventLogger.log("evt").add("name", "notifyDummyEvent");
            }

            public void notifyEvent(DummyEvent event) {
            }

        };
        NotifyEventFunctor<DummyListener, DummyEvent> notifyEventFunctor = DummyListener::notifyDummyEvent;
        EventManager<DummyListener, DummyEvent> eventManager = new EventManager<>();
        eventManager.registerListener(listener1);
        eventManager.fireNotifyEvent(notifyEventFunctor, new DummyEvent(this));
        eventLogger.assertEquals("<log>" + "<evt name='notifyDummyEvent'/>"
                + "</log>");

        eventManager.unregisterListener(listener1);
        eventManager.fireNotifyEvent(notifyEventFunctor, new DummyEvent(this));
        eventLogger.assertEquals("<log>" + "</log>");

    }

    interface DummyListener extends EventListener<DummyEvent> {
        void notifyDummyEvent(DummyEvent event);
    }

    class DummyEvent extends AbstractEvent<Object> {

        public DummyEvent(Object source) {
            super(source);
        }
    }
}
