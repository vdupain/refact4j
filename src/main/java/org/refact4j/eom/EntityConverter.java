package org.refact4j.eom;

public interface EntityConverter<T> extends java.util.function.Function<T, EntityObject> {

    EntityObject convert(T arg);

    default EntityObject apply(T arg) {
        return this.convert(arg);
    }

}
