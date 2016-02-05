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

    public Iterator<T> iterator() {
        return Collections.unmodifiableMap(this).values().iterator();
    }


    public void add(T object) {
        final K key = this.keyifier.keyify(object);
        if (this.containsKey(key)) {
            throw new RuntimeException("Repository already contains object '" + object + "' with key '" + key + "'");
        }
        this.add(key, object);
    }

    public void add(K key, T object) {
        this.put(key, object);
    }

    public boolean contains(T object) {
        return this.containsValue(object);
    }


    protected void setKeyifier(Keyifier<K, T> getKey) {
        this.keyifier = getKey;
    }

}
