package org.refact4j.util;

public class HolderImpl<T> implements Holder<T> {

    private final T t;

    protected HolderImpl(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

}
