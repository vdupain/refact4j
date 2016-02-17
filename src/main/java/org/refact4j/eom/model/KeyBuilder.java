package org.refact4j.eom.model;

import org.refact4j.eom.model.impl.KeyImpl;

public class KeyBuilder {

    private final KeyImpl key;

    private KeyBuilder(EntityDescriptor entityDescriptor) {
        this.key = new KeyImpl(entityDescriptor);
    }

    public static KeyBuilder init(EntityDescriptor entityDescriptor) {
        return new KeyBuilder(entityDescriptor);
    }

    public KeyBuilder set(Field field, Object value) {
        key.add(field, value);
        return this;
    }

    public Key get() {
        return this.key;
    }

}
