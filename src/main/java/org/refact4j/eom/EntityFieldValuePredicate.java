package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.util.EqualsHelper;

/**
 * EntityFieldValuePredicate is an Unary Predicate that returns true if the
 * given value equals the value of the EntityObject for the given field.
 */
public class EntityFieldValuePredicate implements EntityPredicate {
    private Field field;

    private Object value;

    public void setField(Field field) {
        this.field = field;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean eval(EntityObject arg) {
        return EqualsHelper.equals(arg.get(field), value);
    }

}
