package org.refact4j.core;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> {

    T getId();
}
