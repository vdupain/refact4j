package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.eom.impl.EntityDataSetImpl;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.impl.UnmodifiableEntitySetImpl;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.eom.model.impl.KeyImpl;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;
import org.refact4j.model.BarDesc;
import org.refact4j.model.DummyRepository;
import org.refact4j.model.FooDesc;
import org.refact4j.util.ComparatorHelper;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class EntitySetTest {

    public static EntitySet createEntitySetWithDummies() {
        String xmlData = "";
        xmlData += "<Foo name='foo1' id='1'/>";
        xmlData += "<Foo name='foo2' id='2'/>";
        xmlData += "<Foo name='foo3' id='3'/>";

        EntityList entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        return EntitySetBuilder.init().addAll(entityObjects).getEntitySet();
    }

    public static EntitySet createEntitySetWithAnotherDummies() {
        String xmlData = "";
        xmlData += "<Bar name='bar1' id='1'/>";
        xmlData += "<Bar name='bar2' id='2'/>";

        EntityList entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        return EntitySetBuilder.init().addAll(entityObjects).getEntitySet();
    }

    private static EntitySet createEntitySet() {
        EntitySet entityObjectSet1 = createEntitySetWithDummies();
        EntitySet entityObjectSet2 = createEntitySetWithAnotherDummies();
        return EntitySetBuilder.init().addAll(entityObjectSet1).addAll(entityObjectSet2)
                .getEntitySet();
    }

    public static EntitySet createSampleEntitySet() {
        String xmlData = "";
        xmlData += "<Bar name='bar1' id='1'/>";
        xmlData += "<Bar name='bar2' id='2'/>";
        xmlData += "<Foo bar='1' name='foo1' id='1'/>";
        xmlData += "<Foo bar='1' name='foo2' id='2'/>";
        xmlData += "<Foo bar='2' name='foo3' id='3'/>";

        EntityList entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        return EntitySetBuilder.init().addAll(entityObjects).getEntitySet();
    }

    @Test
    public void testEntitySet() {
        String xmlData = "";
        xmlData += "<Foo name='foo1' id='1'/>";
        xmlData += "<Foo name='foo2' id='2'/>";

        EntityList entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        EntityObject entityObject = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<Foo name='foo3' id='3'/>").get(0);
        EntitySet entityObjectSet = EntitySetBuilder.init().addAll(entityObjects).add(entityObject).getEntitySet();

        assertTrue(entityObjectSet.containsAll(entityObjects));
        assertTrue(entityObjectSet.contains(entityObject));
        assertEquals(3, entityObjectSet.size());

        EntityList entityObjectList = new EntityListImpl(entityObjectSet);
        assertTrue(entityObjectList.containsAll(entityObjects));
        assertTrue(entityObjectList.contains(entityObject));
        assertEquals(3, entityObjectList.size());

        assertTrue(entityObjectList.getKeys().contains(entityObject.getKey()));
    }

    @Test
    public void testGetEntitiesByEntityDescriptor() {
        EntitySet entityObjectSet1 = createEntitySetWithDummies();
        EntitySet entityObjectSet2 = createEntitySetWithAnotherDummies();
        EntitySet entityObjectSet = EntitySetBuilder.init().addAll(entityObjectSet1).addAll(entityObjectSet2)
                .getEntitySet();

        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entityObjectSet);
        EntitySet readOnlyEntitySet = new UnmodifiableEntitySetImpl(entityObjectSet);
        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, readOnlyEntitySet);
        EntityDataSetImpl entityObjectDataSet = new EntityDataSetImpl(entityObjectSet);
        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entityObjectDataSet);
        EntityList entityObjectList = new EntityListImpl(entityObjectSet);
        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entityObjectList);
    }

    private void testGetEntitiesByEntityDescriptor(EntityCollection collection1, EntityCollection collection2,
                                                   EntityCollection collection) {
        List<EntityObject> dummies = collection.getAll(FooDesc.INSTANCE);
        EntityComparator entityObjectComparator = new EntityComparator() {

            public int compare(EntityObject o1, EntityObject o2) {
                return ComparatorHelper.compare(o1.getKey(), o2.getKey());
            }
        };

        Collections.sort(dummies, entityObjectComparator);
        assertEquals(collection1.size(), dummies.size());
        EntityList list1 = new EntityListImpl(collection1);
        Collections.sort(list1, entityObjectComparator);
        for (int i = 0; i < dummies.size(); i++) {
            assertEquals(list1.get(i), dummies.get(i));
        }

        List<EntityObject> othersdummies = collection.getAll(BarDesc.INSTANCE);
        Collections.sort(othersdummies, entityObjectComparator);
        assertEquals(collection2.size(), othersdummies.size());
        EntityList list2 = new EntityListImpl(collection2);
        Collections.sort(list2, entityObjectComparator);
        for (int i = 0; i < othersdummies.size(); i++) {
            assertEquals(list2.get(i), othersdummies.get(i));
            assertEquals(list2.get(i), list2.findByIdentifier(list2.get(i).getKey()));
        }

    }

    @Test
    public void testGetEntitiesByEntityDescriptorAndUnaryPredicate() {
        EntitySet entityObjectSet1 = createEntitySetWithDummies();
        EntitySet entityObjectSet = EntitySetBuilder.init().addAll(entityObjectSet1).getEntitySet();
        EntityObject entityObject = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<FooDesc name='dummy4' id='4'/>").get(0);
        entityObjectSet.add(entityObject);

        testGetEntitiesByEntityDescriptorAndUnaryPredicate(entityObjectSet, entityObject);
        EntitySet readOnlyEntitySet = new UnmodifiableEntitySetImpl(entityObjectSet);
        testGetEntitiesByEntityDescriptorAndUnaryPredicate(readOnlyEntitySet, entityObject);
        EntityDataSetImpl entityObjectDataSet = new EntityDataSetImpl(entityObjectSet);
        testGetEntitiesByEntityDescriptorAndUnaryPredicate(entityObjectDataSet, entityObject);
        EntityList entityObjectList = new EntityListImpl(entityObjectSet);
        testGetEntitiesByEntityDescriptorAndUnaryPredicate(entityObjectList, entityObject);
    }

    private void testGetEntitiesByEntityDescriptorAndUnaryPredicate(EntityCollection collection,
                                                                    EntityObject entityObject) {

        EntityPredicate entityObjectPredicate = new EntityPredicate() {
            public Boolean apply(EntityObject arg) {
                return arg.get(FooDesc.ID) == 4;
            }
        };

        List<EntityObject> list = collection.getAll(FooDesc.INSTANCE, entityObjectPredicate);
        assertEquals(1, list.size());
        assertEquals(entityObject, list.get(0));
    }

    @Test
    public void testGetEntitiesFilteredByUnaryPredicate() {
        EntitySet entityObjectSet = createEntitySet();
        EntityObject entityObject = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<FooDesc name='dummy4' id='4'/>").get(0);
        entityObjectSet.add(entityObject);

        testGetEntitiesFilteredByUnaryPredicate(entityObjectSet, entityObject);
        EntitySet readOnlyEntitySet = new UnmodifiableEntitySetImpl(entityObjectSet);
        testGetEntitiesFilteredByUnaryPredicate(readOnlyEntitySet, entityObject);
        EntityDataSetImpl entityObjectDataSet = new EntityDataSetImpl(entityObjectSet);
        testGetEntitiesFilteredByUnaryPredicate(entityObjectDataSet, entityObject);
        EntityList entityObjectList = new EntityListImpl(entityObjectSet);
        testGetEntitiesFilteredByUnaryPredicate(entityObjectList, entityObject);
    }

    private void testGetEntitiesFilteredByUnaryPredicate(EntityCollection collection, EntityObject entityObject) {
        EntityPredicate entityObjectPredicate = new EntityPredicate() {
            public Boolean apply(EntityObject arg) {
                return arg.getEntityDescriptor().equals(FooDesc.INSTANCE) && arg.get(FooDesc.ID) == 4;
            }
        };

        List<EntityObject> list = collection.findByPredicate(entityObjectPredicate);
        assertEquals(1, list.size());
        assertEquals(entityObject, list.get(0));
    }

    @Test
    public void testGetEntitiesFilteredByExprConstraint() {
        EntitySet entityObjectSet = createEntitySet();
        EntityObject entityObject = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<Foo name='foo4' id='4'/>").get(0);
        entityObjectSet.add(entityObject);

        testGetEntitiesFilteredByExprConstraint(entityObjectSet, entityObject);
        EntitySet readOnlyEntitySet = new UnmodifiableEntitySetImpl(entityObjectSet);
        testGetEntitiesFilteredByExprConstraint(readOnlyEntitySet, entityObject);
        EntityDataSetImpl entityObjectDataSet = new EntityDataSetImpl(entityObjectSet);
        testGetEntitiesFilteredByExprConstraint(entityObjectDataSet, entityObject);
        EntityList entityObjectList = new EntityListImpl(entityObjectSet);
        testGetEntitiesFilteredByExprConstraint(entityObjectList, entityObject);
    }

    private void testGetEntitiesFilteredByExprConstraint(EntityCollection collection, EntityObject entityObject) {
        EntityExpression expr = EntityExpressionBuilder.init(FooDesc.ID).equalTo(4).or(
                EntityExpressionBuilder.init(BarDesc.NAME).equalTo("bar1")).getExpression();
        List<EntityObject> list = collection.findByPredicate(expr);
        assertEquals(2, list.size());
        assertTrue(list.contains(entityObject));
        KeyImpl key = new KeyImpl(BarDesc.INSTANCE);
        key.add(BarDesc.ID, 1);
        assertTrue(list.contains(collection.findByIdentifier(key)));
    }

    @Test
    public void testEntitySetContainsEntity() {
        EntitySet entityObjectSet = createEntitySetWithDummies();
        testEntitySetContainsEntity(entityObjectSet);
        EntitySet readOnlyEntitySet = new UnmodifiableEntitySetImpl(entityObjectSet);
        testEntitySetContainsEntity(readOnlyEntitySet);
        EntityDataSetImpl entityObjectDataSet = new EntityDataSetImpl(entityObjectSet);
        testEntitySetContainsEntity(entityObjectDataSet);
        EntityList entityObjectList = new EntityListImpl(entityObjectSet);
        testEntitySetContainsEntity(entityObjectList);
    }

    private void testEntitySetContainsEntity(EntityCollection entityObjectSet) {
        KeyImpl key = new KeyImpl(FooDesc.INSTANCE);
        key.add(FooDesc.ID, 1);
        EntityObject entityObject = entityObjectSet.findByIdentifier(key);
        assertTrue(entityObjectSet.contains(entityObject));
        assertFalse(createEntitySetWithAnotherDummies().contains(entityObject));
    }

    @Test
    public void testEntitySetApplyfunctor() {
        final EntitySet entityObjectSet = createEntitySet();
        testEntitySetApplyfunctor(entityObjectSet);
        EntitySet readOnlyEntitySet = new UnmodifiableEntitySetImpl(entityObjectSet);
        testEntitySetApplyfunctor(readOnlyEntitySet);
        EntityDataSetImpl entityObjectDataSet = new EntityDataSetImpl(entityObjectSet);
        testEntitySetApplyfunctor(entityObjectDataSet);
        EntityList entityObjectList = new EntityListImpl(entityObjectSet);
        testEntitySetApplyfunctor(entityObjectList);
    }

    private void testEntitySetApplyfunctor(final EntityCollection collection) {
        collection.apply(FooDesc.INSTANCE, new EntityFunctor<Object>() {

            public Object apply(EntityObject arg) {
                assertEquals(FooDesc.INSTANCE, arg.getEntityDescriptor());
                return null;
            }
        });
        collection.apply(BarDesc.INSTANCE, new EntityFunctor<Object>() {

            public Object apply(EntityObject arg) {
                assertEquals(BarDesc.INSTANCE, arg.getEntityDescriptor());
                return null;
            }
        });
    }

    @Test
    public void testEntitySetGetEntitiesByEntityDescriptorFieldAndValue() {
        final EntitySet entityObjectSet = createEntitySet();
        testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(entityObjectSet);
        EntitySet readOnlyEntitySet = new UnmodifiableEntitySetImpl(entityObjectSet);
        testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(readOnlyEntitySet);
        EntityDataSetImpl entityObjectDataSet = new EntityDataSetImpl(entityObjectSet);
        testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(entityObjectDataSet);
    }

    private void testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(EntitySet entityObjectSet) {
        EntityCollection entityObjects = entityObjectSet.getEntities(FooDesc.INSTANCE, FooDesc.NAME, "foo1");
        Key key = KeyBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, 1).getKey();
        EntityObject entityObject = entityObjectSet.findByIdentifier(key);
        assertEquals(entityObject, entityObjects.iterator().next());
    }

    @Test
    public void testReadOnlyEntitySet() {
        String xmlData = "";
        xmlData += "<Foo name='foo1' id='1'/>";
        xmlData += "<Foo name='foo2' id='2'/>";

        EntityList entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        EntitySet entityObjectSet = EntitySetBuilder.init().addAll(entityObjects).getReadOnlyEntitySet();

        try {
            entityObjectSet.clear();
            fail("Expected Exception");
        } catch (UnsupportedOperationException e) {
        }
        try {
            entityObjectSet.iterator().remove();
            fail("Expected Exception");
        } catch (UnsupportedOperationException e) {
        }
        try {
            entityObjectSet.add(entityObjects.get(0));
            fail("Expected Exception");
        } catch (UnsupportedOperationException e) {
        }
        try {
            entityObjectSet.remove(entityObjects.get(0));
            fail("Expected Exception");
        } catch (UnsupportedOperationException e) {
        }
        try {
            entityObjectSet.removeAll(entityObjects);
            fail("Expected Exception");
        } catch (UnsupportedOperationException e) {
        }
        try {
            entityObjectSet.addAll(entityObjects);
            fail("Expected Exception");
        } catch (UnsupportedOperationException e) {
        }
        try {
            entityObjectSet.retainAll(entityObjects);
            fail("Expected Exception");
        } catch (UnsupportedOperationException e) {
        }
    }

}
