package org.refact4j.eom;

import org.refact4j.collection.FilterIterator;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.functor.UnaryPredicate;
import org.refact4j.functor.aggregate.AbstractAggregateFunctor;
import org.refact4j.functor.aggregate.AggregateFunctor;
import org.refact4j.functor.aggregate.MaxValue;
import org.refact4j.functor.aggregate.MinValue;
import org.refact4j.util.ComparatorHelper;

import java.util.Collection;

public final class EntityAggregateFunctor implements AggregateFunctor<EntityObject> {

    private final AggregateFunctor<EntityObject> delegate;

    private EntityAggregateFunctor(final Field field, AggregateFunctor<EntityObject> aggregateFunctor) {
        EntityComparator entityObjectComparator = new EntityComparator() {
            public int compare(EntityObject o1, EntityObject o2) {
                return ComparatorHelper.compare(((Comparable<?>) o1.get(field)), ((Comparable<?>) o2.get(field)));
            }
        };
        this.delegate = aggregateFunctor;
        ((AbstractAggregateFunctor<EntityObject>) this.delegate).setComparator(entityObjectComparator);
    }

    public EntityObject eval(Collection<? extends EntityObject> arg) {
        return delegate.eval(arg);
    }

    public static EntityObject applyMaxAggregateFunctor(EntityCollection collection, EntityDescriptor entityDescriptor,
                                                        String fieldName) {
        return applyMaxAggregateFunctor(collection, entityDescriptor.getField(fieldName));
    }

    public static EntityObject applyMinAggregateFunctor(EntityCollection collection, EntityDescriptor entityDescriptor,
                                                        String fieldName) {
        return applyMinAggregateFunctor(collection, entityDescriptor.getField(fieldName));
    }

    public static EntityObject applyMaxAggregateFunctor(EntityCollection collection, final Field field) {
        EntityCollection result = filterByEntityDescriptor(collection, field.getEntityDescriptor());
        return new EntityAggregateFunctor(field, new MaxValue<EntityObject>()).eval(result);
    }

    public static EntityObject applyMinAggregateFunctor(EntityCollection collection, final Field field) {
        EntityCollection result = filterByEntityDescriptor(collection, field.getEntityDescriptor());
        return new EntityAggregateFunctor(field, new MinValue<EntityObject>()).eval(result);
    }

    private static EntityCollection filterByEntityDescriptor(EntityCollection collection,
                                                             final EntityDescriptor entityDescriptor) {
        EntityCollection result = new EntityListImpl();
        FilterIterator<EntityObject> filterIterator = new FilterIterator<EntityObject>(collection.iterator(),
                new UnaryPredicate<EntityObject>() {
                    public Boolean eval(EntityObject entityObject) {
                        return entityObject.getEntityDescriptor().equals(entityDescriptor);
                    }
                });
        while (filterIterator.hasNext()) {
            result.add(filterIterator.next());
        }

        return result;
    }

}
