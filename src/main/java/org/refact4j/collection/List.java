package org.refact4j.collection;

import java.io.Serializable;

public interface List<T, ID extends Serializable, TYPE> extends java.util.List<T>, Collection<T, ID, TYPE> {

}
