package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.eom.impl.EntityDataSet;
import org.refact4j.eom.impl.EntityList;
import org.refact4j.eom.impl.EntitySet;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.eom.model.impl.KeyImpl;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;
import org.refact4j.expr.Expression;
import org.refact4j.expr.ExpressionBuilder;
import org.refact4j.model.BarDesc;
import org.refact4j.model.DummyRepository;
import org.refact4j.model.FooDesc;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class EntitySetTest {

    private static EntitySet createEntitySetWithDummies() {
        String xmlData = "";
        xmlData += "<Foo name='foo1' id='1'/>";
        xmlData += "<Foo name='foo2' id='2'/>";
        xmlData += "<Foo name='foo3' id='3'/>";

        List<EntityObject> entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        return EntitySetBuilder.init().addAll(entityObjects).get();
    }

    private static EntitySet createEntitySetWithAnotherDummies() {
        String xmlData = "";
        xmlData += "<Bar name='bar1' id='1'/>";
        xmlData += "<Bar name='bar2' id='2'/>";

        List<EntityObject> entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        return EntitySetBuilder.init().addAll(entityObjects).get();
    }

    private static EntitySet createEntitySet() {
        EntitySet entityObjectSet1 = createEntitySetWithDummies();
        EntitySet entityObjectSet2 = createEntitySetWithAnotherDummies();
        return EntitySetBuilder.init().addAll(entityObjectSet1).addAll(entityObjectSet2)
                .get();
    }

    public static EntitySet createSampleEntitySet() {
        String xmlData = "";
        xmlData += "<Bar name='bar1' id='1'/>";
        xmlData += "<Bar name='bar2' id='2'/>";
        xmlData += "<Foo bar='1' name='foo1' id='1'/>";
        xmlData += "<Foo bar='1' name='foo2' id='2'/>";
        xmlData += "<Foo bar='2' name='foo3' id='3'/>";

        List<EntityObject> entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        return EntitySetBuilder.init().addAll(entityObjects).get();
    }

    @Test
    public void testEntitySet() {
        String xmlData = "";
        xmlData += "<Foo name='foo1' id='1'/>";
        xmlData += "<Foo name='foo2' id='2'/>";

        List<EntityObject> entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        EntityObject entityObject = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<Foo name='foo3' id='3'/>").get(0);
        EntitySet entityObjectSet = EntitySetBuilder.init().addAll(entityObjects).add(entityObject).get();

        assertTrue(entityObjectSet.containsAll(entityObjects));
        assertTrue(entityObjectSet.contains(entityObject));
        assertEquals(3, entityObjectSet.size());

        List<EntityObject> entityObjectList = new EntityList(entityObjectSet);
        assertTrue(entityObjectList.containsAll(entityObjects));
        assertTrue(entityObjectList.contains(entityObject));
        assertEquals(3, entityObjectList.size());

        assertTrue(entityObjectList.stream().map(EntityObject::getKey).collect(Collectors.toList()).contains(entityObject.getKey()));
    }

    @Test
    public void testGetEntitiesByEntityDescriptor() {
        EntitySet entityObjectSet1 = createEntitySetWithDummies();
        EntitySet entityObjectSet2 = createEntitySetWithAnotherDummies();
        EntitySet entityObjectSet = EntitySetBuilder.init().addAll(entityObjectSet1).addAll(entityObjectSet2)
                .get();

        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entityObjectSet);
        EntitySet entitySet = new EntitySet(entityObjectSet);
        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entitySet);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entityObjectDataSet);
        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entityObjectSet);
    }

    private void testGetEntitiesByEntityDescriptor(EntitySet collection1, EntitySet collection2,
                                                   EntitySet collection) {
        List<EntityObject> dummies = collection.getAll(FooDesc.INSTANCE);
        Comparator<EntityObject> comparator = (o1, o2) -> o1.getKey().compareTo(o2.getKey());

        Collections.sort(dummies, comparator);
        assertEquals(collection1.size(), dummies.size());
        List<EntityObject> list1 = new EntityList(collection1);
        Collections.sort(list1, comparator);
        for (int i = 0; i < dummies.size(); i++) {
            assertEquals(list1.get(i), dummies.get(i));
        }

        List<EntityObject> othersdummies = collection.getAll(BarDesc.INSTANCE);
        Collections.sort(othersdummies, comparator);
        assertEquals(collection2.size(), othersdummies.size());
        EntityList list2 = new EntityList(collection2);
        Collections.sort(list2, comparator);
        for (int i = 0; i < othersdummies.size(); i++) {
            assertEquals(list2.get(i), othersdummies.get(i));
            assertEquals(list2.get(i), list2.findByIdentifier(list2.get(i).getKey()));
        }
    }

    @Test
    public void testGetEntitiesByEntityDescriptorAndUnaryPredicate() {
        EntitySet entityObjectSet1 = createEntitySetWithDummies();
        EntitySet entityObjectSet = EntitySetBuilder.init().addAll(entityObjectSet1).get();
        EntityObject entityObject = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<FooDesc name='dummy4' id='4'/>").get(0);
        entityObjectSet.add(entityObject);

        testGetEntitiesByEntityDescriptorAndUnaryPredicate(entityObjectSet, entityObject);
        EntitySet entitySet = new EntitySet(entityObjectSet);
        testGetEntitiesByEntityDescriptorAndUnaryPredicate(entitySet, entityObject);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testGetEntitiesByEntityDescriptorAndUnaryPredicate(entityObjectDataSet, entityObject);
        testGetEntitiesByEntityDescriptorAndUnaryPredicate(entityObjectSet, entityObject);
    }

    private void testGetEntitiesByEntityDescriptorAndUnaryPredicate(EntitySet collection,
                                                                    EntityObject entityObject) {
        List<EntityObject> list = collection.getAll(FooDesc.INSTANCE).stream()
                .filter(arg -> arg.get(FooDesc.ID) == 4).collect(Collectors.toList());
        assertEquals(1, list.size());
        assertEquals(entityObject, list.get(0));
    }

    @Test
    public void testGetEntitiesFilteredByUnaryPredicate() {
        EntitySet entityObjectSet = createEntitySet();
        EntityObject entityObject = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<FooDesc name='dummy4' id='4'/>").get(0);
        entityObjectSet.add(entityObject);

        testGetEntitiesFilteredByUnaryPredicate(entityObjectSet, entityObject);
        EntitySet entitySet = new EntitySet(entityObjectSet);
        testGetEntitiesFilteredByUnaryPredicate(entitySet, entityObject);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testGetEntitiesFilteredByUnaryPredicate(entityObjectDataSet, entityObject);
        testGetEntitiesFilteredByUnaryPredicate(entityObjectSet, entityObject);
    }

    private void testGetEntitiesFilteredByUnaryPredicate(EntitySet collection, EntityObject entityObject) {
        Predicate<EntityObject> entityObjectPredicate = arg -> arg.getEntityDescriptor().equals(FooDesc.INSTANCE) && arg.get(FooDesc.ID) == 4;
        List<EntityObject> list = collection.stream().filter(entityObjectPredicate).collect(Collectors.toList());
        assertEquals(1, list.size());
        assertEquals(entityObject, list.get(0));
    }

    @Test
    public void testGetEntitiesFilteredByExprConstraint() {
        EntitySet entityObjectSet = createEntitySet();
        EntityObject entityObject = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<Foo name='foo4' id='4'/>").get(0);
        entityObjectSet.add(entityObject);

        testGetEntitiesFilteredByExprConstraint(entityObjectSet, entityObject);
        EntitySet entitySet = new EntitySet(entityObjectSet);
        testGetEntitiesFilteredByExprConstraint(entitySet, entityObject);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testGetEntitiesFilteredByExprConstraint(entityObjectDataSet, entityObject);
        testGetEntitiesFilteredByExprConstraint(entityObjectSet, entityObject);
    }

    private void testGetEntitiesFilteredByExprConstraint(EntitySet collection, EntityObject entityObject) {
        Expression<EntityObject> expr = ExpressionBuilder.init(FooDesc.ID).equalTo(4).or(
                ExpressionBuilder.init(BarDesc.NAME).equalTo("bar1")).get();
        List<EntityObject> list = collection.stream().filter(expr).collect(Collectors.toList());
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
        EntitySet entitySet = new EntitySet(entityObjectSet);
        testEntitySetContainsEntity(entitySet);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testEntitySetContainsEntity(entityObjectDataSet);
        testEntitySetContainsEntity(entityObjectSet);
    }

    private void testEntitySetContainsEntity(EntitySet entityObjectSet) {
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
        EntitySet entityObjects = new EntitySet(entityObjectSet);
        testEntitySetApplyfunctor(entityObjects);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testEntitySetApplyfunctor(entityObjectDataSet);
        List<EntityObject> entityObjectList = new EntityList(entityObjectSet);
        testEntitySetApplyfunctor(entityObjectList);
    }

    private void testEntitySetApplyfunctor(final Collection<EntityObject> collection) {
        collection.stream()
                .filter(p -> p.getEntityDescriptor().equals(FooDesc.INSTANCE))
                .forEach(arg -> assertEquals(FooDesc.INSTANCE, arg.getEntityDescriptor()));
        collection.stream()
                .filter(p -> p.getEntityDescriptor().equals(BarDesc.INSTANCE))
                .forEach(arg -> assertEquals(BarDesc.INSTANCE, arg.getEntityDescriptor()));
    }

    @Test
    public void testEntitySetGetEntitiesByEntityDescriptorFieldAndValue() {
        final EntitySet entityObjectSet = createEntitySet();
        testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(entityObjectSet);
        EntitySet readOnlyEntitySet = new EntitySet(entityObjectSet);
        testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(readOnlyEntitySet);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(entityObjectDataSet);
    }

    private void testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(EntitySet entityObjectSet) {
        Collection<EntityObject> entityObjects = entityObjectSet.stream()
                .filter(e -> Objects.equals(e.get(FooDesc.NAME), "foo1"))
                .collect(Collectors.toList());
        Key key = KeyBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, 1).getKey();
        EntityObject entityObject = entityObjectSet.findByIdentifier(key);
        assertEquals(entityObject, entityObjects.iterator().next());
    }

}
