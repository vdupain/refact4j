package org.refact4j.eom.xml;

import org.junit.Test;
import org.refact4j.eom.impl.EntityDataSet;
import org.refact4j.model.DummyRepository;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

import static org.junit.Assert.assertEquals;


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
        dataset2XmlConverter
                .register(new EntityXmlDescriptor(DummyRepository.get()));
        EntityDataSet dataset = new EntityDataSet();
        dataset2XmlConverter.unmarshal(input, dataset);

        String actual = dataset2XmlConverter.marshal(dataset);
        EntityDataSet actualDataset = new EntityDataSet();
        dataset2XmlConverter.unmarshal(actual, actualDataset);

        assertEquals(dataset2XmlConverter.marshal(dataset), dataset2XmlConverter.marshal(actualDataset));
    }

}
