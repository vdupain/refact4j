package org.refact4j.util;

import java.util.Set;

public interface Repository<K, T> extends Iterable<T> {

    T get(K key);

    boolean contains(T object);

    boolean containsKey(K key);

    Set<K> keySet();

    interface Keyifier<K, T> {
        K keyify(T object);
    }

}
