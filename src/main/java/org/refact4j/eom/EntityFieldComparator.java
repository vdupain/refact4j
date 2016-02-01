package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.functor.CompositeUnaryPredicate;
import org.refact4j.functor.ConstantFunction;
import org.refact4j.visitor.Visitor;

import java.util.function.BiFunction;

/**
 * EntityFieldComparator is an Unary Predicate for comparing a EntityObject
 * field value.
 *
 * @param <T>
 */
public class EntityFieldComparator<T> extends CompositeUnaryPredicate<EntityObject> {

    private final T value;

    private final Field field;

    public EntityFieldComparator(BiFunction<T, T, Boolean> biFunction, Field field, T value) {
        super(biFunction, new GetEntityFieldFunction(field), new ConstantFunction<Object>(value));
        this.field = field;
        this.value = value;
    }

    public Field getField() {
        return field;
    }

    public T getValue() {
        return value;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof EntityFieldComparatorVisitor) {
            ((EntityFieldComparatorVisitor) visitor).visitEntityFieldComparator(this);
        }
    }

    public interface EntityFieldComparatorVisitor extends Visitor {
        void visitEntityFieldComparator(EntityFieldComparator<?> fieldComparator);
    }
}
