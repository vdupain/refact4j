package org.refact4j.eom.xml;

import org.junit.Before;
import org.junit.Test;
import org.refact4j.eom.*;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;
import org.refact4j.eom.xml.writer.EntityXmlWriterHelper;
import org.refact4j.model.BarDesc;
import org.refact4j.model.DummyRepository;
import org.refact4j.model.FooDesc;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EntityXmlTest {
    static Date date = new Date();
    static final String DUMMY_XML =
            "<Foo bar=\"99\" bar2=\"null\" beginDate=\"" + EntityUtils.formatDate(date)
                    + "\" endDate=\"null\" flag=\"false\" id=\"1\" name=\"dummy\" timestampDate=\"null\" value=\"1.23\"/>";
    EntityObject entity1;
    EntityObject entity2;
    Integer id = 1;
    Integer id99 = 99;
    String name = "dummy";
    Double value = 1.23;

    private static Object getFieldValue(List<EntityObject> list, int entityObjectIndex, Field field) {
        return list.get(entityObjectIndex).get(field);
    }

    @Before
    public void setUp() throws Exception {
        entity2 = EntityObjectBuilder.init(BarDesc.INSTANCE).set(BarDesc.ID, id99)
                .set(BarDesc.NAME, name).set(BarDesc.VALUE, value).getEntity();
        entity1 = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, id).set(
                FooDesc.NAME, name).set(FooDesc.VALUE, value).set(FooDesc.BEGIN_DATE, date)
                .set(FooDesc.FLAG, false).set(FooDesc.BAR, entity2).getEntity();
    }

    @Test
    public void testNominal() throws Exception {
        EntityList list = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<DummyBean id='1' value='1.1'/>"
                + "<DummyBean id='2'/>");
        assertEquals(2, list.size());
        assertEquals(1.1, getFieldValue(list, 0, FooDesc.VALUE));
        assertEquals(null, getFieldValue(list, 1, FooDesc.VALUE));
    }

    @Test
    public void testDateField() throws Exception {
        EntityList list = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<DummyBean id='1' beginDate='10/10/00'/>");
        assertEquals(1, list.size());
        assertEquals(EntityUtils.parseDate("10/10/00"), getFieldValue(list, 0, FooDesc.BEGIN_DATE));
    }

    @Test
    public void testTimestampField() throws Exception {
        EntityList list = EntityXmlReaderHelper.parse(FooDesc.INSTANCE,
                "<DummyBean id='1' timestampDate='11/27/78-15:40:20'/>");
        assertEquals(1, list.size());
        assertEquals(EntityUtils.parseTimestamp("11/27/78-15:40:20"), getFieldValue(list, 0, FooDesc.TIMESTAMP));
    }

    @Test
    public void testBooleanField() throws Exception {
        EntityList list = EntityXmlReaderHelper.parse(FooDesc.INSTANCE, "<DummyBean id='1' flag='true'/>"
                + "<DummyBean id='2' flag='false'/>");

        assertEquals(2, list.size());
        assertEquals(Boolean.TRUE, getFieldValue(list, 0, FooDesc.FLAG));
        assertEquals(Boolean.FALSE, getFieldValue(list, 1, FooDesc.FLAG));
    }

    @Test
    public void testWithMultipleTypes() throws Exception {
        EntityList list = EntityXmlReaderHelper.parse(DummyRepository.get(), "<Foo id='1' flag='true'/>"
                + "<Bar id='1' name='abcdef' />");
        assertEquals(2, list.size());
        EntityObject firstEntity = list.get(0);
        assertEquals(FooDesc.INSTANCE, firstEntity.getEntityDescriptor());
        assertEquals(Boolean.TRUE, firstEntity.get(FooDesc.FLAG));
        assertEquals((Integer) 1, firstEntity.get(FooDesc.ID));

        EntityObject secondEntity = list.get(1);
        assertEquals(BarDesc.INSTANCE, secondEntity.getEntityDescriptor());
        assertEquals("abcdef", secondEntity.get(BarDesc.NAME));
    }

    @Test
    public void testDummyEntity2Xml() {
        assertEquals(DUMMY_XML, entity1.toXmlString());
    }

    @Test
    public void testDummyXml2Entity() {
        XmlEntityConverter functor = new XmlEntityConverter();
        functor.setEntityDescriptorRepository(DummyRepository.get());
        EntityObject entityObject = functor.convert(DUMMY_XML);
        assertEquals(DUMMY_XML, entityObject.toXmlString());
    }

    @Test
    public void testEntityXmlReader() {
        testEntityXmlReader(null);
        testEntityXmlReader("entities");
    }

    public void testEntityXmlReader(String rootTag) {
        EntityList expectedEntityList = new EntityListImpl();
        expectedEntityList.add(entity1);
        expectedEntityList.add(entity2);
        String expectedXmlData;

        expectedXmlData = EntityXmlWriterHelper.dump(expectedEntityList, rootTag);

        EntityList actualEntityList;
        actualEntityList = EntityXmlReaderHelper.parse(DummyRepository.get(), expectedXmlData);
        for (int i = 0; i < expectedEntityList.size(); i++) {
            EntityObject expectedEntity = expectedEntityList.get(i);
            EntityObject actualManagedObject = actualEntityList.get(i);
            assertTrue(EntityUtils.equals(expectedEntity, actualManagedObject));
            assertTrue(expectedEntity.equals(actualManagedObject));
        }

        expectedEntityList.clear();
        expectedEntityList.add(entity1);
        expectedXmlData = EntityXmlWriterHelper.dump(expectedEntityList, rootTag);
        actualEntityList = EntityXmlReaderHelper.parse(entity1.getEntityDescriptor(), expectedXmlData);
        for (int i = 0; i < expectedEntityList.size(); i++) {
            EntityObject expectedEntity = expectedEntityList.get(i);
            EntityObject actualManagedObject = actualEntityList.get(i);
            assertTrue(EntityUtils.equals(expectedEntity, actualManagedObject));
            assertTrue(expectedEntity.equals(actualManagedObject));
        }

    }

}
