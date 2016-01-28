package org.refact4j.xml;

import org.junit.Before;
import org.junit.Test;
import org.refact4j.collection.Set;
import org.refact4j.eom.EntityTestUtils;
import org.refact4j.eom.impl.EntityDataSet;
import org.refact4j.eom.impl.EntityList;
import org.refact4j.eom.xml.EntityXmlDescriptor;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;
import org.refact4j.eom.xml.writer.EntityXmlWriterHelper;
import org.refact4j.model.DummyRepository;
import org.refact4j.util.StringHelper;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

import java.io.InputStream;
import java.io.Reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EntityXmlParsingTest {

    private Dataset2XmlConverterImpl dataset2xmlconverter;

    @Before
    public void setUp() throws Exception {
        this.dataset2xmlconverter = new Dataset2XmlConverterImpl();
        this.dataset2xmlconverter.register(new EntityXmlDescriptor(DummyRepository.get()));
        this.dataset2xmlconverter.register(new DummyXmlDescriptor());
    }

    @Test
    public void testXmlReader() throws Exception {
        String xmlData1, xmlData2;
        org.refact4j.eom.EntityList entityObjects1, entityObjects2;

        InputStream xmlInputStream = this.getClass().getResourceAsStream("/org/refact4j/xml/dataset1.xml");
        entityObjects1 = EntityXmlReaderHelper.unmarshal(DummyRepository.get(), StringHelper
                .getStringFromUTF8File(xmlInputStream));
        xmlData1 = EntityXmlWriterHelper.dump(entityObjects1);

        xmlInputStream = this.getClass().getResourceAsStream("/org/refact4j/xml/dataset2.xml");

        xmlData2 = StringHelper.getStringFromUTF8File(xmlInputStream);

        Set dataset = new EntityDataSet();
        this.dataset2xmlconverter.unmarshal(xmlData2, dataset);

        entityObjects2 = new EntityList(dataset);
        EntityTestUtils.assertEquals(entityObjects2, xmlData1, "entities");
    }

    @Test
    public void testXmlWriter() throws Exception {
        InputStream xmlInputStream = this.getClass().getResourceAsStream("/org/refact4j/xml/dataset2.xml");
        String xmlData = StringHelper.getStringFromUTF8File(xmlInputStream);
        Set initialDataset = new EntityDataSet();
        this.dataset2xmlconverter.unmarshal(xmlData, initialDataset);

        Dataset2XmlConverterImpl converter = new Dataset2XmlConverterImpl();
        converter.register(new EntityXmlDescriptor(DummyRepository.get()));
        Set actualDataset = new EntityDataSet();
        this.dataset2xmlconverter.unmarshal(converter.marshal(initialDataset), actualDataset);
        EntityTestUtils.assertEquals(new EntityList(initialDataset), new EntityList(actualDataset));
    }

    @Test
    public void testNullReader() {
        try {
            this.dataset2xmlconverter.unmarshal((Reader) null, null);
            fail("RuntimeException Expected");
        } catch (RuntimeException e) {
            assertEquals("java.net.MalformedURLException", e.getMessage());
        }
    }

    @Test
    public void testFailXsd() throws Exception {
        InputStream xmlInputStream = this.getClass().getResourceAsStream("dataset2.xml");

        String xml = StringHelper.getStringFromUTF8File(xmlInputStream);
        xml = xml.replaceFirst("dataset2.xsd", "unknow.xsd");
        try {
            this.dataset2xmlconverter.unmarshal(xml, new EntityDataSet());
            fail("RuntimeException Expected");
        } catch (RuntimeException e) {
        }
    }

}
