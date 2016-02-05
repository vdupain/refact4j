package org.refact4j.collection;

import org.junit.Assert;

import java.util.Iterator;


public class ChangeSetTest extends AbstractChangeSetTest {
    private MyBean bean1;

    protected List populateInitial() {
        List collection = new List();
        collection.add("a1");
        collection.add("a2");
        collection.add("a3");

        bean1 = new MyBean();
        bean1.setId(1);
        bean1.setValue("abc");
        collection.add(bean1);
        return collection;
    }

    protected void generateDelta() {
        //create
        collectionDecorator.create("new1");
        collectionDecorator.create(1.23);
        //delete
        collectionDecorator.delete("a2");
        //update
        bean1.setValue("xxx");
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
