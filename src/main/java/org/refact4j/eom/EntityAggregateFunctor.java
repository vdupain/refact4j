package org.refact4j.eom;

import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.function.aggregate.AbstractAggregateFunctor;
import org.refact4j.function.aggregate.AggregateFunctor;
import org.refact4j.function.aggregate.MaxValue;
import org.refact4j.function.aggregate.MinValue;
import org.refact4j.util.ComparatorHelper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class EntityAggregateFunctor implements AggregateFunctor<EntityObject> {

    private final AggregateFunctor<EntityObject> delegate;

    private EntityAggregateFunctor(final Field field, AggregateFunctor<EntityObject> aggregateFunctor) {
        EntityComparator entityObjectComparator = (EntityComparator) (o1, o2) -> ComparatorHelper.compare(((Comparable<?>) o1.get(field)), ((Comparable<?>) o2.get(field)));
        this.delegate = aggregateFunctor;
        ((AbstractAggregateFunctor<EntityObject>) this.delegate).setComparator(entityObjectComparator);
    }

    public static EntityObject applyMaxAggregateFunctor(Collection<EntityObject> collection, EntityDescriptor entityDescriptor,
                                                        String fieldName) {
        return applyMaxAggregateFunctor(collection, entityDescriptor.getField(fieldName));
    }

    public static EntityObject applyMinAggregateFunctor(Collection<EntityObject> collection, EntityDescriptor entityDescriptor,
                                                        String fieldName) {
        return applyMinAggregateFunctor(collection, entityDescriptor.getField(fieldName));
    }

    public static EntityObject applyMaxAggregateFunctor(Collection<EntityObject> collection, final Field field) {
        Collection<EntityObject> result = filterByEntityDescriptor(collection, field.getEntityDescriptor());
        return new EntityAggregateFunctor(field, new MaxValue<>()).apply(result);
    }

    public static EntityObject applyMinAggregateFunctor(Collection<EntityObject> collection, final Field field) {
        Collection<EntityObject> result = filterByEntityDescriptor(collection, field.getEntityDescriptor());
        return new EntityAggregateFunctor(field, new MinValue<>()).apply(result);
    }

    private static Collection<EntityObject> filterByEntityDescriptor(Collection<EntityObject> collection,
                                                                     final EntityDescriptor entityDescriptor) {
        List<EntityObject> list = collection.stream().filter(p -> p.getEntityDescriptor().equals(entityDescriptor))
                .collect(Collectors.toList());
        return new EntityListImpl(list);
    }

    public EntityObject apply(Collection<? extends EntityObject> arg) {
        return delegate.apply(arg);
    }

}
