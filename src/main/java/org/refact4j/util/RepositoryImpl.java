package org.refact4j.util;

import java.util.*;

public class RepositoryImpl<K, T> extends HashMap<K, T> implements Repository<K, T> {

    private Keyifier<K, T> keyifier;

    public T get(Object key) {
        T object = super.get(key);
        if (object == null) {
            throw new RuntimeException("Missing Object with Key '" + key + "' in repository");
        }
        return object;
    }

    public void add(T object) {
        final K key = this.keyifier.keyify(object);
        if (this.containsKey(key)) {
            throw new RuntimeException("Repository already contains object '" + object + "' with key '" + key + "'");
        }
        this.put(key, object);
    }

    protected void setKeyifier(Keyifier<K, T> getKey) {
        this.keyifier = getKey;
    }

}
