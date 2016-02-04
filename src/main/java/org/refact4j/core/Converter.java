package org.refact4j.core;

/**
 * Created by vince on 04/02/2016.
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}