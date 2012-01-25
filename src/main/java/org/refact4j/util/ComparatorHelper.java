package org.refact4j.util;

public final class ComparatorHelper {

    private ComparatorHelper() {
    }

    @SuppressWarnings("unchecked")
    public static int compare(Comparable c1, Comparable c2) {
        if ((c1 == null) && (c2 == null)) {
            return 0;
        }
        if (c1 == null) {
            return -1;
        }
        if (c2 == null) {
            return 1;
        }
        return c1.compareTo(c2);
    }

}
