package org.refact4j.eom.model;

import org.refact4j.expr.Expression;

import java.io.Externalizable;
import java.io.Serializable;

/**
 * Key contains fields and values that compose the key of the EntityObject. Key
 * is used for EntityObject equality using the built-in equals(). The key of a
 * EntityObject is describe in the EntityDescriptor. A Key can be unique or
 * compound.
 */
public interface Key extends Comparable<Key>, Serializable, Externalizable {

    EntityDescriptor getEntityDescriptor();

    Object getFieldValue(Field field);

    boolean isUnique();

    boolean isCompound();

    Expression asExpression();
}
