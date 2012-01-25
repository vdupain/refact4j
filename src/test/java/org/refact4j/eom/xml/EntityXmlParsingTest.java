package org.refact4j.eom.xml;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.refact4j.collection.DataSet;
import org.refact4j.eom.EntityList;
import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.impl.EntityDataSetImpl;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.model.DummyRepository;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;


public class EntityXmlParsingTest {

    @Test
    public void testXml() throws Exception {
        String input = "<dataset>"
                + " <Bar name='bar2' id='2'>"
                + "        <Foo name='foo3' id='3'/>"
                + " </Bar>"
                + " <Bar name='bar1' id='1'>"
                + "        <Foo name='foo2' id='2'/>"
                + "        <Foo name='foo1' id='1'/>"
                + " </Bar>"
                + "    <Foo name='foo4' id='4'/>"
                + "    <Foo name='foo5' id='5'/>"
                + "    <Employee id='100'>"
                + "         <EmployeeInfo id='200' firstName='xxx' lastName='zzz'/>"
                + "    </Employee>"
                + "</dataset>";

        Dataset2XmlConverterImpl dataset2XmlConverter = new Dataset2XmlConverterImpl();
        DataSet dataset = new EntityDataSetImpl();
        dataset2XmlConverter
                .register(new EntityXmlDescriptor(DummyRepository.get()));
        dataset2XmlConverter.unmarshal(input, dataset);

        EntityList entityObjects = new EntityListImpl(dataset);

        assertEquals(9, entityObjects.size());
        assertEquals(
                "<Bar id=\"2\" name=\"bar2\" value=\"null\"/>",
                EntityStringifier.XML.stringify(entityObjects.get(0)));
        assertEquals(
                "<Foo bar=\"2\" bar2=\"null\" beginDate=\"null\" endDate=\"null\" flag=\"null\" id=\"3\" name=\"foo3\" timestampDate=\"null\" value=\"null\"/>",
                EntityStringifier.XML.stringify(entityObjects.get(1)));
        assertEquals(
                "<Bar id=\"1\" name=\"bar1\" value=\"null\"/>",
                EntityStringifier.XML.stringify(entityObjects.get(2)));
        assertEquals(
                "<Foo bar=\"1\" bar2=\"null\" beginDate=\"null\" endDate=\"null\" flag=\"null\" id=\"2\" name=\"foo2\" timestampDate=\"null\" value=\"null\"/>",
                EntityStringifier.XML.stringify(entityObjects.get(3)));
        assertEquals(
                "<Foo bar=\"1\" bar2=\"null\" beginDate=\"null\" endDate=\"null\" flag=\"null\" id=\"1\" name=\"foo1\" timestampDate=\"null\" value=\"null\"/>",
                EntityStringifier.XML.stringify(entityObjects.get(4)));
        assertEquals(
                "<Foo bar=\"null\" bar2=\"null\" beginDate=\"null\" endDate=\"null\" flag=\"null\" id=\"4\" name=\"foo4\" timestampDate=\"null\" value=\"null\"/>",
                EntityStringifier.XML.stringify(entityObjects.get(5)));
        assertEquals(
                "<Foo bar=\"null\" bar2=\"null\" beginDate=\"null\" endDate=\"null\" flag=\"null\" id=\"5\" name=\"foo5\" timestampDate=\"null\" value=\"null\"/>",
                EntityStringifier.XML.stringify(entityObjects.get(6)));

        assertEquals("<Employee id=\"100\" info=\"200\"/>", EntityStringifier.XML.stringify(entityObjects.get(7)));
        assertEquals("<EmployeeInfo employee=\"100\" firstName=\"xxx\" id=\"200\" lastName=\"zzz\"/>", EntityStringifier.XML.stringify(entityObjects.get(8)));
    }

}
