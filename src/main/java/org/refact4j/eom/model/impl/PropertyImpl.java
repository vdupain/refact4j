package org.refact4j.eom.model.impl;

import org.refact4j.eom.model.Property;
import org.refact4j.util.RepositoryImpl;

public class PropertyImpl extends RepositoryImpl<Object, Object> implements Property {

    private static final long serialVersionUID = 7878468812170283853L;

    public PropertyImpl() {
        setKeyifier(new Keyifier<Object, Object>() {

            public Object keyify(Object object) {
                return object;
            }

        });
    }

    public void addProperty(Object key, Object value) {
        if (this.containsKey(key)) {
            throw new RuntimeException("Property with key '" + key + "' already exists");
        }
        add(key, value);
    }

    public Object getProperty(Object key) {
        try {
            return get(key);
        } catch (RuntimeException e) {
            // ignored
        }
        return null;
    }

    public Property getProperty() {
        return this;
    }

}
