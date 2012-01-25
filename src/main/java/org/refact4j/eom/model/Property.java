package org.refact4j.eom.model;

import java.io.Serializable;

public interface Property extends Serializable {

    void addProperty(Object key, Object value);

    Object getProperty(Object key);

    Property getProperty();

}