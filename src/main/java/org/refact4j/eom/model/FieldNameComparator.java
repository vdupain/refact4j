package org.refact4j.eom.model;

import java.io.Serializable;
import java.util.Comparator;

public class FieldNameComparator implements Comparator<Field>, Serializable {

    private static final long serialVersionUID = 6597218930459174049L;

    public int compare(final Field field1, final Field field2) {
        return field1.getName().compareTo(field2.getName());
    }

}
