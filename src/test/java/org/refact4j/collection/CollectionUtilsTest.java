package org.refact4j.collection;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.refact4j.functor.UnaryPredicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class CollectionUtilsTest {

    @Test
    public void testFilterCollection() {
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        collection.add(3.0);
        collection.add("aaa");
        collection.add("bbb");
        Date now = new Date();
        collection.add(now);
        Collection<Object> filteredCollection = CollectionHelper
                .filter(collection, new UnaryPredicate<Object>() {

                    public Boolean eval(Object arg) {
                        return arg instanceof String;
                    }
                });
        assertEquals(2, filteredCollection.size());
        filteredCollection = CollectionHelper.filter(collection,
                new UnaryPredicate<Object>() {

                    public Boolean eval(Object arg) {
                        return arg instanceof Date;
                    }
                });
        assertEquals(1, filteredCollection.size());
        assertEquals(now, filteredCollection.iterator().next());
        filteredCollection = CollectionHelper.filter(collection,
                new UnaryPredicate<Object>() {

                    public Boolean eval(Object arg) {
                        return arg instanceof Double;
                    }
                });
        assertEquals(1, filteredCollection.size());
        assertEquals(3.0, filteredCollection.iterator().next());
    }
}
