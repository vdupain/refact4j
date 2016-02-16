package org.refact4j.eom;

import org.junit.Assert;
import org.refact4j.collection.AbstractChangeSetTest;
import org.refact4j.collection.ChangeSetDelta;
import org.refact4j.collection.ChangeSetImpl;
import org.refact4j.eom.impl.ChangeSetEntityObjectListener;
import org.refact4j.eom.impl.EntitySet;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;
import org.refact4j.evt.EventLogger;
import org.refact4j.model.DummyRepository;
import org.refact4j.model.FooDesc;

import java.util.ArrayList;

public class EntityChangeSetTest extends AbstractChangeSetTest {
    private EntityObject foo;
    private EntityObject foo3;
    private final EventLogger eventLogger = new EventLogger();

    protected java.util.List populateInitial() {
        java.util.List list = new ArrayList();
        list.addAll(EntitySetTest.createSampleEntitySet());
        return list;
    }

    protected void generateDelta() {
        EntitySet entitySet = new EntitySet();
        entitySet.addAll(collectionDecorator.getCollection());
        for (EntityObject e : entitySet) {
            e.registerListener(new ChangeSetEntityObjectListener((ChangeSetImpl<EntityObject>) changeSet));
            e.registerListener(new EntityObjectListener() {

                public void notifyEntityObjectChange(EntityObjectEvent event, ChangeSetDelta<EntityObject> delta) {
                    eventLogger.log("delta").add("source", event.getSource().getKey()).add("property",
                            delta.getProperty()).add("old", delta.getOldValue()).add("new", delta.getNewValue());
                }

                public void notifyEvent(EntityObjectEvent event) {
                }
            });
        }

        foo3 = entitySet.stream()
                .filter(p -> p.getKey().equals(KeyBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, 3).getKey()))
                .findFirst().get();

        // update
        foo3.set(FooDesc.VALUE, 1.2345);
        foo3.set(FooDesc.FLAG, true);
        foo3.set(FooDesc.VALUE, 0);
        // delete
        collectionDecorator.delete(foo3);
        // create
        String xmlData = "<Foo name='foo5' id='5' value='55'/>";
        foo = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData).get(0);
        collectionDecorator.create(foo);
    }

    protected void assertDelta() {
        final EventLogger actualEventLogger = new EventLogger();
        java.util.List<EntityObject> updated = changeSet.getUpdatedObjects();
        for (EntityObject entityObject : updated) {
            for (Object o : changeSet.getDeltas(entityObject)) {
                ChangeSetDelta<EntityObject> delta = (ChangeSetDelta<EntityObject>) o;
                actualEventLogger.log("delta").add("source", delta.getSource().getKey()).add("property",
                        delta.getProperty()).add("old", delta.getOldValue()).add("new", delta.getNewValue());
            }
        }

        actualEventLogger.assertEquals(eventLogger);
        java.util.Collection<EntityObject> deleted = changeSet.getDeletedObjects();
        Assert.assertEquals(foo3, deleted.iterator().next());

        java.util.Collection<EntityObject> created = changeSet.getCreatedObjects();
        Assert.assertEquals(foo.toXmlString(), created.iterator().next().toXmlString());

    }
}