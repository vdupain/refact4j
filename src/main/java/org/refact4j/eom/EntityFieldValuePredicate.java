package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.util.EqualsHelper;

import java.util.function.Predicate;

/**
 * EntityFieldValuePredicate is an Unary Predicate that returns true if the
 * given value equals the value of the EntityObject for the given field.
 */
public class EntityFieldValuePredicate implements Predicate<EntityObject> {
    private Field field;

    private Object value;

    public void setField(Field field) {
        this.field = field;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean test(EntityObject arg) {
        return EqualsHelper.equals(arg.get(field), value);
    }

}
