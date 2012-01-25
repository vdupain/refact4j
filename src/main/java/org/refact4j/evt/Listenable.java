package org.refact4j.evt;

/**
 * A tagging interface that all listenable interfaces must extend.
 *
 * @see EventListener
 */
public interface Listenable<T extends EventListener<?>> {

    void registerListener(T listener);

    void unregisterListener(T listener);
}
