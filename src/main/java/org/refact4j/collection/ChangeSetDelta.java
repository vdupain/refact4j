package org.refact4j.collection;

public interface ChangeSetDelta<T> {

    T getSource();

    String getProperty();

    Object getOldValue();

    Object getNewValue();

}
