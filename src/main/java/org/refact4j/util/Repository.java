package org.refact4j.util;

import java.util.HashMap;

public class Repository<K, T> extends HashMap<K, T> {

    public T get(Object key) {
        T object = super.get(key);
        if (object == null) {
            throw new RuntimeException("Missing Object with Key '" + key + "' in repository");
        }
        return object;
    }

    public void add(K key, T object) {
        if (this.containsKey(key)) {
            throw new RuntimeException("Repository already contains object '" + object + "' with key '" + key + "'");
        }
        this.put(key, object);
    }

}
