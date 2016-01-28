package org.refact4j.collection;

import org.junit.Assert;
import org.refact4j.collection.impl.ListImpl;

import java.util.Iterator;


public class ChangeSetTest extends AbstractChangeSetTest {
    MyBean myBean;

    protected List populateInitial() {
        List collection = new ListImpl();
        collection.add("a1");
        collection.add("a2");
        collection.add("a3");

        myBean = new MyBean();
        myBean.setId(1);
        myBean.setValue("abc");
        collection.add(myBean);
        return collection;
    }

    protected void generateDelta() {
        collectionDecorator.create("new1");
        collectionDecorator.create(1.23);
        collectionDecorator.delete("a2");
        myBean.setValue("xxx");
    }

    protected void assertDelta() {
        java.util.Collection created = changeSet.getCreatedObjects();
        Assert.assertEquals(2, created.size());
        Iterator iter = created.iterator();
        Assert.assertEquals("new1", iter.next());
        Assert.assertEquals(1.23, iter.next());

        java.util.Collection deleted = changeSet.getDeletedObjects();
        Assert.assertEquals(1, deleted.size());
        Assert.assertEquals("a2", deleted.iterator().next());
    }


    private class MyBean {
        private int id;
        private String value;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
