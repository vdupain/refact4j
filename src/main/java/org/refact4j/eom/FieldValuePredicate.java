package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.function.comparison.Equal;

/**
 * FieldValuePredicate is an Unary Predicate that returns true if the given
 * value equals the value of the EntityObject for the given field.
 *
 * @param <T>
 */
public class FieldValuePredicate<T> extends EntityFieldComparator<T> {

    public FieldValuePredicate(Field field, T value) {
        super(new Equal<>(), field, value);
    }

}
