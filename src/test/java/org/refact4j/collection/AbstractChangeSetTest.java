package org.refact4j.collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public abstract class AbstractChangeSetTest {
    protected CollectionDecorator collectionDecorator;
    protected ChangeSet changeSet;

    @Before
    public void setUp() throws Exception {
        collectionDecorator = new CollectionDecorator(populateInitial());
        changeSet = collectionDecorator.getChangeSet();
    }

    protected abstract List populateInitial();

    protected abstract void generateDelta();

    protected abstract void assertDelta();

    private void assertChangeSetEmpty(boolean isEmpty) {
        //Assert.assertEquals(isEmpty, changeSet.getUpdatedObjects().isEmpty());
        Assert.assertEquals(isEmpty, changeSet.getCreatedObjects().isEmpty());
        Assert.assertEquals(isEmpty, changeSet.getDeletedObjects().isEmpty());
    }

    @Test
    public void testDelta() {
        changeSet.startRecordChanges();
        generateDelta();
        changeSet.stopRecordChanges();
        assertDelta();
    }

    @Test
    public void testChangeSetNotRecordedBeforeStarted() {
        assertChangeSetEmpty(true);
        generateDelta();
        assertChangeSetEmpty(true);
    }

    @Test
    public void testChangeSetRecordedAfterStarted() {
        changeSet.startRecordChanges();
        assertChangeSetEmpty(true);
        generateDelta();
        assertChangeSetEmpty(false);
    }

    @Test
    public void testChangeSetNotRecordedAfterStoped() {
        changeSet.stopRecordChanges();
        generateDelta();
        assertChangeSetEmpty(true);
    }

    @Test
    public void testChangeSetResettedOnStart() {
        changeSet.startRecordChanges();
        assertChangeSetEmpty(true);
        generateDelta();
        assertChangeSetEmpty(false);
        changeSet.startRecordChanges();
        assertChangeSetEmpty(true);
    }
}