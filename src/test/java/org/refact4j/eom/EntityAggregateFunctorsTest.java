package org.refact4j.eom;

import org.junit.Before;
import org.junit.Test;
import org.refact4j.eom.impl.EntitySet;
import org.refact4j.eom.metamodel.DefaultEntityDescriptorRepoFactory;
import org.refact4j.eom.metamodel.EOMMetaModelRepository;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;

import static org.junit.Assert.assertEquals;


public class EntityAggregateFunctorsTest {

    private EntityDescriptor fooEntityDescriptor;

    private EntityDescriptor barEntityDescriptor;

    private EntitySet entityObjects;

    @Before
    public void setUp() throws Exception {
        String metaModelXml = "<dataset>"
                + "<entityDescriptor name='foo'>"
                + "    <field name='id' isKey='true' dataType='integer'/>"
                + "    <field name='field1' dataType='string'/>"
                + "    <field name='value' dataType='double'/>"
                + "    <field name='bars' dataType='oneToMany' target='bar'/>"
                + "</entityDescriptor>"
                + "<entityDescriptor name='bar'>"
                + "    <field name='id' isKey='true' dataType='integer'/>"
                + "    <field name='field1' dataType='string'/>"
                + "    <field name='field2' dataType='string'/>"
                + "    <field name='foo' dataType='manyToOne' target='foo' prettyName='ToOne Relation to Foo Object'/>"
                + "</entityDescriptor>" + "</dataset>";
        EntityDescriptorRepository repository = DefaultEntityDescriptorRepoFactory.init(
                EOMMetaModelRepository.get(), metaModelXml, null)
                .createEntityDescriptorRepository();
        fooEntityDescriptor = repository.getEntityDescriptor("foo");
        barEntityDescriptor = repository.getEntityDescriptor("bar");
        String dataXml = "<dataset>" +
                "<foo id='1' field1='b' value='9'/>" +
                "<foo id='2' field1='c' value='8' >" +
                "    <bar id='11' field1='bar11_1' field2='bar11_2'/>" +
                "    <bar id='12' field1='x_bar12_1' field2='bar12_2'/>" +
                "</foo>" +
                "<foo id='3' field1='a' value='7'/>" +
                "<bar id='13' field1='a_bar13_1' field2='bar12_2'/>" +
                "<bar id='14' field1='bar14_1' field2='bar14_2' foo='1'/>" +
                "</dataset>";
        entityObjects = new EntitySet(EntityXmlReaderHelper.unmarshal(repository, dataXml));
    }

    @Test
    public void testMin() {
        EntityObject entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects,
                fooEntityDescriptor.getField("id"));
        assertEquals("<foo field1=\"b\" id=\"1\" value=\"9.0\"/>", entityObject.toXmlString());

        entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects, fooEntityDescriptor.getField("field1"));
        assertEquals("<foo field1=\"a\" id=\"3\" value=\"7.0\"/>", entityObject.toXmlString());

        entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects, fooEntityDescriptor,
                "value");
        assertEquals("<foo field1=\"a\" id=\"3\" value=\"7.0\"/>", entityObject.toXmlString());

        entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects, barEntityDescriptor.getField("id"));
        assertEquals("<bar field1=\"bar11_1\" field2=\"bar11_2\" foo=\"2\" id=\"11\"/>", entityObject.toXmlString());

        entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects, barEntityDescriptor.getField("field1"));
        assertEquals("<bar field1=\"a_bar13_1\" field2=\"bar12_2\" foo=\"null\" id=\"13\"/>", entityObject.toXmlString());

        entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects, barEntityDescriptor,
                "foo");
        assertEquals("<bar field1=\"a_bar13_1\" field2=\"bar12_2\" foo=\"null\" id=\"13\"/>", entityObject.toXmlString());

    }

    @Test
    public void testMax() {
        EntityObject entityObject = EntityAggregateFunctor.applyMaxAggregateFunctor(entityObjects,
                fooEntityDescriptor.getField("id"));
        assertEquals("<foo field1=\"a\" id=\"3\" value=\"7.0\"/>", entityObject.toXmlString());

        entityObject = EntityAggregateFunctor.applyMaxAggregateFunctor(entityObjects, fooEntityDescriptor.getField("field1"));
        assertEquals("<foo field1=\"c\" id=\"2\" value=\"8.0\"/>", entityObject.toXmlString());

        entityObject = EntityAggregateFunctor.applyMaxAggregateFunctor(entityObjects, fooEntityDescriptor,
                "value");
        assertEquals("<foo field1=\"b\" id=\"1\" value=\"9.0\"/>", entityObject.toXmlString());

        entityObject = EntityAggregateFunctor.applyMaxAggregateFunctor(entityObjects, barEntityDescriptor.getField("id"));
        assertEquals("<bar field1=\"bar14_1\" field2=\"bar14_2\" foo=\"1\" id=\"14\"/>", entityObject.toXmlString());

        entityObject = EntityAggregateFunctor.applyMaxAggregateFunctor(entityObjects, barEntityDescriptor.getField("field1"));
        assertEquals("<bar field1=\"x_bar12_1\" field2=\"bar12_2\" foo=\"2\" id=\"12\"/>", entityObject.toXmlString());
    }

}
