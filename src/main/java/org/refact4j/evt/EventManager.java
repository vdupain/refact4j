package org.refact4j.evt;

/**
 * Default event manager class.
 *
 * @see EventListener
 * @see EventListenerList
 * @see Listenable
 */
public class EventManager<T extends EventListener<E>, E extends Event<?>> implements Listenable<T> {

    private final EventListenerList<T, E> listenerList = new EventListenerList<T, E>();

    public synchronized void fireNotifyEvent(NotifyEventFunctor<T, E> notifyEventFunctor, E event) {
        T[] listeners = listenerList.getListenerList();
        if (listeners != null) {
            for (T listener : listeners) {
                notifyEventFunctor.notifyEvent(listener, event);
            }
        }
    }

    public synchronized void fireNotifyEvent(E event) {
        T[] listeners = listenerList.getListenerList();
        if (listeners != null) {
            for (T listener : listeners) {
                listener.notifyEvent(event);
            }
        }
    }

    public synchronized void registerListener(T listener) {
        listenerList.add(listener);
    }

    public synchronized void unregisterListener(T listener) {
        listenerList.remove(listener);
    }

    public interface NotifyEventFunctor<T extends EventListener<E>, E extends Event<?>> {
        void notifyEvent(T listener, E event);

    }

}