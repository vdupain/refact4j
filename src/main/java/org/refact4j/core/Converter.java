package org.refact4j.core;

@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}