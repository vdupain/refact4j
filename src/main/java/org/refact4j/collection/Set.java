package org.refact4j.collection;

import java.io.Serializable;

public interface Set<T, ID extends Serializable, TYPE> extends java.util.Set<T>, Collection<T, ID, TYPE> {

}
