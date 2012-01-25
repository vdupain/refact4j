package org.refact4j.collection.impl;

import org.refact4j.core.IdResolver;

public class HashCodeIdResolver implements IdResolver {

    public Object getId(Object t) {
        return t.hashCode();
    }

}
