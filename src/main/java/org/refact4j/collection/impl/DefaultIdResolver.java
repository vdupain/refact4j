package org.refact4j.collection.impl;

import org.refact4j.core.IdResolver;
import org.refact4j.core.Identifiable;

import java.io.Serializable;

public class DefaultIdResolver<ID> implements IdResolver<Identifiable<ID>, ID> {

    public ID getId(Identifiable<ID> t) {
        return t.getId();
    }

}
