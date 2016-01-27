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
import java.util.List;
import java.util.stream.Collectors;

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

    public EntityObject apply(Collection<? extends EntityObject> arg) {
        return delegate.apply(arg);
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
        return new EntityAggregateFunctor(field, new MaxValue<EntityObject>()).apply(result);
    }

    public static EntityObject applyMinAggregateFunctor(EntityCollection collection, final Field field) {
        EntityCollection result = filterByEntityDescriptor(collection, field.getEntityDescriptor());
        return new EntityAggregateFunctor(field, new MinValue<EntityObject>()).apply(result);
    }

    private static EntityCollection filterByEntityDescriptor(EntityCollection collection,
                                                             final EntityDescriptor entityDescriptor) {
        List<EntityObject> list = collection.stream().filter(p -> p.getEntityDescriptor().equals(entityDescriptor))
                .collect(Collectors.toList());
       return new EntityListImpl(list);
    }

}
