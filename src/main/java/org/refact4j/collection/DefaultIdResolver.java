package org.refact4j.collection;

import org.refact4j.core.IdResolver;
import org.refact4j.core.Identifiable;

public class DefaultIdResolver<ID> implements IdResolver<Identifiable<ID>, ID> {

    public ID getId(Identifiable<ID> t) {
        return t.getId();
    }

}
