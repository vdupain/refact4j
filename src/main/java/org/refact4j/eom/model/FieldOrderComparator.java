package org.refact4j.eom.model;

import java.io.Serializable;
import java.util.Comparator;

public class FieldOrderComparator implements Comparator<Field>, Serializable {

    private static final long serialVersionUID = -4910005560626690788L;

    public int compare(final Field field1, final Field field2) {
        return field1.getOrder().compareTo(field2.getOrder());
    }

}
