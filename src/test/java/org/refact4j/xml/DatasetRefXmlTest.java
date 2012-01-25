package org.refact4j.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.refact4j.collection.DataSet;
import org.refact4j.eom.EntityList;
import org.refact4j.eom.EntityTestUtils;
import org.refact4j.eom.impl.EntityDataSetImpl;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.metamodel.DefaultEntityDescriptorRepoFactory;
import org.refact4j.eom.metamodel.EOMMetaModelRepository;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.xml.EntityXmlDescriptor;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;


public class DatasetRefXmlTest {

    private static String XML = "<dataset>"
            + "    <datasetRef file='/org/refact4j/xml/test_datasetref.xml'/>"
            + "    <foo id='1' name='foo1'/>" + "    <foo id='2' name='foo2'/>"
            + "</dataset>";

    private static final String META_MODEL_XML = "<dataset>"
            + "<entityDescriptor name='foo'>"
            + "     <field name='id' dataType='integer' isKey='true'/>"
            + "    <field name='name' dataType='string'/>" + "</entityDescriptor>"
            + "<entityDescriptor name='bar'>"
            + "     <field name='id' dataType='integer'  isKey='true'/>"
            + "    <field name='name' dataType='string'/>" + "</entityDescriptor>"
            + "</dataset>";

    private EntityDescriptorRepository entityDescriptorRepository;

    private Dataset2XmlConverterImpl dataset2XmlConverter;

    private DataSet dataset;

    @Before
    public void setUp() throws Exception {
        entityDescriptorRepository = DefaultEntityDescriptorRepoFactory.init(
                EOMMetaModelRepository.get(), META_MODEL_XML, null)
                .createEntityDescriptorRepository();
        dataset2XmlConverter = new Dataset2XmlConverterImpl();
        dataset = new EntityDataSetImpl();
        dataset2XmlConverter.register(new EntityXmlDescriptor(entityDescriptorRepository));
    }

    @Test
    public void testDatasetRef() throws Exception {
        dataset2XmlConverter.unmarshal(XML, dataset);

        String expectedXml = "<bar name='bar1' id='11'/>"
                + "<bar name='bar2' id='22'/>" + "<foo name='foo1' id='1'/> "
                + "<foo name='foo2' id='2'/>";
        EntityList expectedEntities = EntityXmlReaderHelper.parse(entityDescriptorRepository,
                expectedXml);
        EntityTestUtils.assertEquals(expectedEntities, new EntityListImpl(dataset));

    }

    @Test
    public void testDatasetRefFileNotFound() {
        XML = XML.replace("test_datasetref.xml", "unkown.xml");
        try {
            dataset2XmlConverter.unmarshal(XML, dataset);
            fail("RuntimeException Expected");
        } catch (RuntimeException e) {
            assertEquals(
                    "java.lang.RuntimeException: dataset file '/org/refact4j/xml/unkown.xml' not found",
                    e.getMessage());
        }
    }
}
