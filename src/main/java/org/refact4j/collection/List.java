package org.refact4j.collection;

import org.refact4j.core.Finder;
import org.refact4j.core.IdResolver;
import org.refact4j.core.TypeResolver;

public interface List<T, ID, TYPE> extends java.util.List<T>, Finder<T, ID, TYPE> {

}
