package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.eom.impl.EntityDataSet;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.impl.EntitySetImpl;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.eom.model.impl.KeyImpl;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;
import org.refact4j.expr.Expression;
import org.refact4j.expr.ExpressionBuilder;
import org.refact4j.model.BarDesc;
import org.refact4j.model.DummyRepository;
import org.refact4j.model.FooDesc;
import org.refact4j.util.ComparatorHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class EntitySetTest {

    public static EntitySet createEntitySetWithDummies() {
        String xmlData = "";
        xmlData += "<Foo name='foo1' id='1'/>";
        xmlData += "<Foo name='foo2' id='2'/>";
        xmlData += "<Foo name='foo3' id='3'/>";

        org.refact4j.eom.EntityList entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        return EntitySetBuilder.init().addAll(entityObjects).getEntitySet();
    }

    public static EntitySet createEntitySetWithAnotherDummies() {
        String xmlData = "";
        xmlData += "<Bar name='bar1' id='1'/>";
        xmlData += "<Bar name='bar2' id='2'/>";

        org.refact4j.eom.EntityList entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
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

        org.refact4j.eom.EntityList entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        return EntitySetBuilder.init().addAll(entityObjects).getEntitySet();
    }

    @Test
    public void testEntitySet() {
        String xmlData = "";
        xmlData += "<Foo name='foo1' id='1'/>";
        xmlData += "<Foo name='foo2' id='2'/>";

        org.refact4j.eom.EntityList entityObjects = EntityXmlReaderHelper.parse(DummyRepository.get(), xmlData);
        EntityObject entityObject = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<Foo name='foo3' id='3'/>").get(0);
        EntitySet entityObjectSet = EntitySetBuilder.init().addAll(entityObjects).add(entityObject).getEntitySet();

        assertTrue(entityObjectSet.containsAll(entityObjects));
        assertTrue(entityObjectSet.contains(entityObject));
        assertEquals(3, entityObjectSet.size());

        org.refact4j.eom.EntityList entityObjectList = new EntityListImpl(entityObjectSet);
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
                .getEntitySet();

        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entityObjectSet);
        EntitySet entitySet = new EntitySetImpl(entityObjectSet);
        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entitySet);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entityObjectDataSet);
        testGetEntitiesByEntityDescriptor(entityObjectSet1, entityObjectSet2, entityObjectSet);
    }

    private void testGetEntitiesByEntityDescriptor(EntitySet collection1, EntitySet collection2,
                                                   EntitySet collection) {
        List<EntityObject> dummies = collection.getAll(FooDesc.INSTANCE);
        EntityComparator entityObjectComparator = (EntityComparator) (o1, o2) -> ComparatorHelper.compare(o1.getKey(), o2.getKey());

        Collections.sort(dummies, entityObjectComparator);
        assertEquals(collection1.size(), dummies.size());
        org.refact4j.eom.EntityList list1 = new EntityListImpl(collection1);
        Collections.sort(list1, entityObjectComparator);
        for (int i = 0; i < dummies.size(); i++) {
            assertEquals(list1.get(i), dummies.get(i));
        }

        List<EntityObject> othersdummies = collection.getAll(BarDesc.INSTANCE);
        Collections.sort(othersdummies, entityObjectComparator);
        assertEquals(collection2.size(), othersdummies.size());
        org.refact4j.eom.EntityList list2 = new EntityListImpl(collection2);
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
        EntitySet entitySet = new EntitySetImpl(entityObjectSet);
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
        EntitySet entitySet = new EntitySetImpl(entityObjectSet);
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
        EntitySet entitySet = new EntitySetImpl(entityObjectSet);
        testGetEntitiesFilteredByExprConstraint(entitySet, entityObject);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testGetEntitiesFilteredByExprConstraint(entityObjectDataSet, entityObject);
        testGetEntitiesFilteredByExprConstraint(entityObjectSet, entityObject);
    }

    private void testGetEntitiesFilteredByExprConstraint(EntitySet collection, EntityObject entityObject) {
        Expression<EntityObject> expr = ExpressionBuilder.init(FooDesc.ID).equalTo(4).or(
                ExpressionBuilder.init(BarDesc.NAME).equalTo("bar1")).getExpression();
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
        EntitySet entitySet = new EntitySetImpl(entityObjectSet);
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
        EntitySet entityObjects = new EntitySetImpl(entityObjectSet);
        testEntitySetApplyfunctor(entityObjects);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testEntitySetApplyfunctor(entityObjectDataSet);
        org.refact4j.eom.EntityList entityObjectList = new EntityListImpl(entityObjectSet);
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
        EntitySet readOnlyEntitySet = new EntitySetImpl(entityObjectSet);
        testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(readOnlyEntitySet);
        EntityDataSet entityObjectDataSet = new EntityDataSet(entityObjectSet);
        testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(entityObjectDataSet);
    }

    private void testEntitySetGetEntitiesByEntityDescriptorFieldAndValue(EntitySet entityObjectSet) {
        Collection<EntityObject> entityObjects = entityObjectSet.getEntities(FooDesc.INSTANCE, FooDesc.NAME, "foo1");
        Key key = KeyBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, 1).getKey();
        EntityObject entityObject = entityObjectSet.findByIdentifier(key);
        assertEquals(entityObject, entityObjects.iterator().next());
    }

}
