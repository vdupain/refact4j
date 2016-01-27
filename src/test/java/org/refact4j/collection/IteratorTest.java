package org.refact4j.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.refact4j.eom.EntityCollection;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.expr.Expression;
import org.refact4j.expr.ExpressionBuilder;
import org.refact4j.functor.UnaryPredicate;
import org.refact4j.model.FooDesc;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;


public class IteratorTest {

    @Test
    public void testIterator() {
        EntityCollection items = new EntityListImpl();
        for (int i = 0; i < 10; i++) {
            EntityObject entityObject;
            if (i % 2 == 0)
                entityObject = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE,
                        (double) 101).getEntity();
            else
                entityObject = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE,
                        (double) i).getEntity();
            items.add(entityObject);
        }

        Iterator<EntityObject> i = items.iterator();
        java.util.function.Function<EntityObject,Double> getItemPrice = new Function<EntityObject, Double>() {
            public Double apply(EntityObject entityObject) {
                return entityObject.get(FooDesc.VALUE);
            }

            public String toString() {
                return FooDesc.VALUE.getName();
            }
        };
        Double usd100 = (double) 100;
        Expression expression = ExpressionBuilder.init(getItemPrice).greaterOrEqual(usd100).getExpression();

        FilterIterator<EntityObject> filteredIterator = new FilterIterator<EntityObject>(i, expression);
        Summer summer = new Summer();
        CollectionHelper.foreach(filteredIterator, summer);
        assertEquals(505, summer.getSum());
    }

    @Test
    public void testFilteredIterator() {
        FilterIterator<Object> filteredIterator = new FilterIterator<Object>(Collections.EMPTY_LIST.iterator(),
                new UnaryPredicate<Object>() {
                    public Boolean apply(Object arg) {
                        return Boolean.TRUE;
                    }
                });
        CollectionHelper.foreach(filteredIterator, new Function<Object, Object>() {

            public Object apply(Object arg) {
                return null;
            }

        });
        try {
            filteredIterator.remove();
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
        }

        assertFalse(filteredIterator.hasNext());
        try {
            filteredIterator.next();
            fail("Expected Exception");
        } catch (NoSuchElementException e) {
        }
    }

    static class Summer implements java.util.function.Function<EntityObject, Integer> {
        private int sum = 0;

        public Integer apply(EntityObject arg) {
            return sum += (arg.get(FooDesc.VALUE));
        }

        public int getSum() {
            return sum;
        }
    }

}
