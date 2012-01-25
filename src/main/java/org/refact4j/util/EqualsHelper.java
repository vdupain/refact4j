package org.refact4j.util;

public final class EqualsHelper {

    private EqualsHelper() {
    }

    public static boolean equals(Object o1, Object o2) {
        return o1 == o2 || (o1 != null && o2 != null && o1.equals(o2));
    }

}
