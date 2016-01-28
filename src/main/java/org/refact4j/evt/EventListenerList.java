package org.refact4j.evt;

/**
 * A class that holds a list of EventListeners.
 * <p>
 * Usage example: Say one is defining a class that sends out FooEvents, and one
 * wants to allow users of the class to register FooListeners and receive
 * notification when FooEvents occur. The following should be added to the class
 * definition:
 * <p>
 * <pre>
 *  EventListenerList listenerList = new EventListenerList();
 *  FooEvent fooEvent = null;
 *  public void registerListener(FooListener listener) {
 *      listenerList.add(listener);
 *  }
 *  public void unregisterListener(FooListener listener) {
 *      listenerList.reentityObjectve(listener);
 *  }
 *  // Notify all listeners that have registered interest for
 *  // notification on this event type.
 *  protected void fireFooXXX() {
 *      EventListener[] listeners = listenerList.getListenerList();
 *      for (int i = 0; i &lt; listeners.length; i++) {
 * 	        if (fooEvent == null)
 *                 fooEvent = new FooEvent(this);
 *             ((FooListener) listeners[i]).fooXXX(fooEvent);
 *      }
 *  }
 * </pre>
 * <p>
 * foo should be changed to the appropriate name, and fireFooXxx to the
 * appropriate method name. One fire method should exist for each notification
 * method in the FooListener interface.
 * <p>
 *
 * @see EventListener
 */

@SuppressWarnings("unchecked")
public class EventListenerList<T extends EventListener<E>, E extends Event<?>> {
    private transient EventListener[] listenerList = new EventListener[]{};

    public synchronized T[] getListenerList() {
        return (T[]) listenerList;
    }

    public synchronized void add(T listener) {
        if (listener == null) {
            throw new IllegalArgumentException("null listener");
        }

        if (listenerList == null) {
            listenerList = new EventListener[]{listener};
        } else {
            int oldLength = listenerList.length;
            EventListener[] tmp = new EventListener[oldLength + 1];
            System.arraycopy(listenerList, 0, tmp, 0, oldLength);
            tmp[oldLength] = listener;
            listenerList = tmp;
        }
    }

    public synchronized boolean remove(T listener) {
        if (listener == null) {
            throw new IllegalArgumentException("null listener");
        }
        int index = -1;
        for (int i = listenerList.length - 1; i >= 0; i--) {
            if (listenerList[i].equals(listener)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            EventListener[] tmp = new EventListener[listenerList.length - 1];
            System.arraycopy(listenerList, 0, tmp, 0, index);
            if (index < tmp.length) {
                System.arraycopy(listenerList, index + 1, tmp, index, tmp.length - index);
            }
            listenerList = (tmp.length == 0) ? null : tmp;
            return true;
        }
        return false;
    }

}