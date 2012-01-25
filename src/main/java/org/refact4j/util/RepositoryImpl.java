package org.refact4j.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RepositoryImpl<K, T> implements Repository<K, T> {

    private final Map<K, T> objects = new HashMap<K, T>();

    private Keyifier<K, T> keyifier;

    public T get(K key) {
        T object = this.objects.get(key);
        if (object == null) {
            throw new RuntimeException("Missing Object with Key '" + key + "' in repository");
        }
        return object;
    }

    public Iterator<T> iterator() {
        return Collections.unmodifiableMap(this.objects).values().iterator();
    }

    public Set<K> keySet() {
        return Collections.unmodifiableMap(this.objects).keySet();
    }

    public void add(T object) {
        final K key = this.keyifier.keyify(object);
        if (this.objects.containsKey(key)) {
            throw new RuntimeException("Repository already contains object '" + object + "' with key '" + key + "'");
        }
        this.add(key, object);
    }

    public void add(K key, T object) {
        this.objects.put(key, object);
    }

    public boolean contains(T object) {
        return this.objects.containsValue(object);
    }

    public boolean containsKey(K key) {
        return this.objects.containsKey(key);
    }

    protected void setKeyifier(Keyifier<K, T> getKey) {
        this.keyifier = getKey;
    }

    public Keyifier<K, T> getKeyifier() {
        return this.keyifier;
    }

}
