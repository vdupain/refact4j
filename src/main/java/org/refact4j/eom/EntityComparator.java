package org.refact4j.eom;

import java.io.Serializable;
import java.util.Comparator;

/**
 * EntityComparator interface is EntityObject comparison function, which imposes
 * a total ordering on some collection of EntityObjects.
 */
public interface EntityComparator extends Comparator<EntityObject>, Serializable {

}
