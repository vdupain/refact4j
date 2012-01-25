package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.functor.BinaryFunctor;
import org.refact4j.functor.CompositeUnaryPredicate;
import org.refact4j.functor.ConstantUnaryFunctor;
import org.refact4j.visitor.Visitor;

/**
 * EntityFieldComparator is an Unary Predicate for comparing a EntityObject
 * field value.
 *
 * @param <T>
 */
public class EntityFieldComparator<T> extends CompositeUnaryPredicate<EntityObject> {

    private final T value;

    private final Field field;

    public EntityFieldComparator(BinaryFunctor<T, T, Boolean> binaryFunctor, Field field, T value) {
        super(binaryFunctor, new GetEntityFieldFunctor(field), new ConstantUnaryFunctor<Object>(value));
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
