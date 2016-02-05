package org.refact4j.model;

import org.junit.Before;
import org.junit.Test;
import org.refact4j.collection.Set;
import org.refact4j.eom.*;
import org.refact4j.eom.impl.DefaultEntityStringifierRepoVisitor;
import org.refact4j.eom.impl.DefaultMetaModelVisitor;
import org.refact4j.eom.impl.EntityDataSet;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.metamodel.DefaultEntityDescriptorRepoFactory;
import org.refact4j.eom.metamodel.EOMMetaModelRepository;
import org.refact4j.eom.metamodel.EntityStringifierXmlDescriptor;
import org.refact4j.eom.metamodel.xml.EOMXmlDescriptor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.impl.Stringifiers;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

import static org.junit.Assert.assertEquals;


public class MetaModelTest {

    private static final String META_MODEL_XML =
            "<dataset>"
                    + "<entityDescriptorRepository>"
                    + "<entityDescriptor name='foo'>"
                    + "    <field name='id' order='2' isKey='true' dataType='integer' defaultValue='-1'/>"
                    + "     <field name='timestampDate' dataType='date'/>"
                    + "    <field name='field1' prettyName='a field #1' dataType='string' defaultValue='azerty'/>"
                    + "    <field name='field2' dataType='string' minLength='3' maxLength='5' />"
                    + "    <field name='bars' order='1' dataType='oneToMany' target='bar' inverseRelation='foo' prettyName='ToMany Relation to Bar Object'>"
                    + "        <property key='key1' value='false' dataType='boolean'/>"
                    + "        <property key='key2' value='value2' dataType='string'/>"
                    + "    </field>"
                    + "</entityDescriptor>"
                    + "<entityDescriptor name='bar'>"
                    + "    <field name='id' isKey='true' dataType='integer'/>"
                    + "    <field name='field1' dataType='string'/>"
                    + "    <field name='field2' dataType='string'/>"
                    + "    <field name='foo' dataType='manyToOne' target='foo' inverseRelation='bars' prettyName='ToOne Relation to Foo Object'/>"
                    + "</entityDescriptor>"
                    + "<entityDescriptor name='version'>"
                    + "    <field name='id' isKey='true' dataType='integer' minValue='1' maxValue='5'/>"
                    + "    <field name='name' dataType='string'/>"
                    + "    <field name='beginDate' dataType='date'/>"
                    + "    <field name='endDate'  dataType='date'/>"
                    + "    <field name='foo' dataType='manyToOne' target='foo' prettyName='ToOne Relation to Foo Object'/>"
                    + "</entityDescriptor>" + "</entityDescriptorRepository>" + "</dataset>";

    private EntityDescriptorRepository repository;

    private EntityStringifierRepo stringifierRepository;

    private EntityDescriptor fooEntityDescriptor;

    @Before
    public void setUp() throws Exception {
        repository =
                DefaultEntityDescriptorRepoFactory.init(EOMMetaModelRepository.get(), META_MODEL_XML,
                        null).createEntityDescriptorRepository();
        String STRINGIFIER_XML = "<dataset>" + "<stringifiers>" + "<stringifier name='fooStringifier' entityDescriptor='foo'>"
                + "    <append id='1' string='id=' field='id'/>"
                + "    <append id='2' string=', field1=' field='field1'/>" + "</stringifier>"
                + "<stringifier name='barStringifier' entityDescriptor='bar'>"
                + "    <append id='1' string='f1=' field='field1'/>"
                + "    <append id='2' string=', f2=' field='field2'/>" + "</stringifier>"
                + "<stringifier name='versionStringifier' entityDescriptor='version'>"
                + "    <append id='1' string='version n°' field='id'/>"
                + "    <append id='2' string='-' field='name'/>" + "    <append id='3' string=' du '/>"
                + "    <append id='4' field='beginDate'/>"
                + "    <append id='5' string=' au ' field='endDate'/>" + "</stringifier>"
                + "</stringifiers>" + "</dataset>";
        stringifierRepository =
                EntityStringifierRepoFactory.init(repository, STRINGIFIER_XML)
                        .createEntityStringifierRepository();
        fooEntityDescriptor = repository.getEntityDescriptor("foo");
    }

    @Test
    public void testNominal() {
        String DATA_XML = "<dataset>" + "<foo id='1' field1='foo1' field2='a' />"
                + "<foo id='2' field1='foo2' field2='b'>"
                + "    <bar id='12' field1='bar12_1' field2='bar12_2'/>" + "</foo>" + "<foo id='3'/>"
                + "<bar id='11' field1='bar11_1' field2='bar11_2' foo='2'/>"
                + "<bar id='13' field1='bar13_1' field2='bar12_2'/>"
                + "<bar id='14' field1='bar14_1' field2='bar14_2' foo='1'/>"
                + "<version id='1' name='v1' beginDate='01/01/07' endDate='12/31/07'/>"
                + "<version id='2' name='v2' beginDate='06/01/07' endDate='06/30/07' foo='2'/>"
                + "</dataset>";
        org.refact4j.eom.EntityList dataEntities = EntityXmlReaderHelper.unmarshal(repository, DATA_XML);
        EntitySet entityObjectSet = EntitySetBuilder.init().addAll(dataEntities).getEntitySet();
        EntityFieldValuePredicate getEntityByKeyPredicate = new EntityFieldValuePredicate();
        getEntityByKeyPredicate.setField(repository.getEntityDescriptor("version").getField("id"));
        getEntityByKeyPredicate.setValue(2);
        EntityObject version2 =
                entityObjectSet.getAll(repository.getEntityDescriptor("version")).stream()
                        .filter(getEntityByKeyPredicate).findFirst().get();
        assertEquals(
                "<version beginDate=\"06/01/07\" endDate=\"06/30/07\" foo=\"2\" id=\"2\" name=\"v2\"/>",
                Stringifiers.ENTITY_XML.stringify(version2));
        assertEquals("version n°2-v2 du 06/01/07 au 06/30/07", stringifierRepository.get(
                repository.getEntityDescriptor("version")).stringify(version2));
        getEntityByKeyPredicate.setField(fooEntityDescriptor.getField("id"));
        getEntityByKeyPredicate.setValue(1);
        EntityObject foo1 = entityObjectSet.getAll(fooEntityDescriptor).stream()
                .filter(getEntityByKeyPredicate).findFirst().get();
        assertEquals("<foo field1=\"foo1\" field2=\"a\" id=\"1\" timestampDate=\"null\"/>",
                Stringifiers.ENTITY_XML.stringify(foo1));
        assertEquals("id=1, field1=foo1", stringifierRepository.get(foo1.getEntityDescriptor())
                .stringify(foo1));
        getEntityByKeyPredicate.setField(fooEntityDescriptor.getField("id"));
        getEntityByKeyPredicate.setValue(3);
        EntityObject foo3 = entityObjectSet.getAll(fooEntityDescriptor).stream()
                .filter(getEntityByKeyPredicate).findFirst().get();
        assertEquals("<foo field1=\"null\" field2=\"null\" id=\"3\" timestampDate=\"null\"/>",
                Stringifiers.ENTITY_XML.stringify(foo3));
        assertEquals("id=3, field1=null", stringifierRepository.get(foo1.getEntityDescriptor())
                .stringify(foo3));
        getEntityByKeyPredicate.setField(repository.getEntityDescriptor("bar").getField("id"));
        getEntityByKeyPredicate.setValue(11);
        EntityObject bar11 =
                entityObjectSet.getAll(repository.getEntityDescriptor("bar")).stream()
                        .filter(getEntityByKeyPredicate).findFirst().get();
        assertEquals("<bar field1=\"bar11_1\" field2=\"bar11_2\" foo=\"2\" id=\"11\"/>",
                Stringifiers.ENTITY_XML.stringify(bar11));
        assertEquals("f1=bar11_1, f2=bar11_2", stringifierRepository
                .get(bar11.getEntityDescriptor()).stringify(bar11));
        getEntityByKeyPredicate.setField(repository.getEntityDescriptor("bar").getField("id"));
        getEntityByKeyPredicate.setValue(13);
        EntityObject bar13 =
                entityObjectSet.getAll(repository.getEntityDescriptor("bar")).stream()
                        .filter(getEntityByKeyPredicate).findFirst().get();
        assertEquals("<bar field1=\"bar13_1\" field2=\"bar12_2\" foo=\"null\" id=\"13\"/>",
                Stringifiers.ENTITY_XML.stringify(bar13));
        assertEquals("f1=bar13_1, f2=bar12_2", stringifierRepository
                .get(bar13.getEntityDescriptor()).stringify(bar13));
    }

    @Test
    public void testEntityDescriptorRepo2Xml() {
        DefaultMetaModelVisitor visitor = new DefaultMetaModelVisitor();
        this.repository.accept(visitor);
        Set initialDataset = visitor.getDataSet();
        Set actualDataset = new EntityDataSet();
        Dataset2XmlConverterImpl converter = new Dataset2XmlConverterImpl();
        converter.register(new EOMXmlDescriptor(EOMMetaModelRepository.get()));
        converter.unmarshal(visitor.toXmlString(), actualDataset);
        EntityTestUtils.assertEquals(new EntityListImpl(initialDataset), new EntityListImpl(actualDataset));
    }

    @Test
    public void testStringifierRepo2Xml() {
        DefaultEntityStringifierRepoVisitor visitor =
                new DefaultEntityStringifierRepoVisitor(this.repository);
        this.stringifierRepository.accept(visitor);
        Set initialDataset = visitor.getDataSet();
        Set actualDataset = new EntityDataSet();
        Dataset2XmlConverterImpl converter = new Dataset2XmlConverterImpl();
        converter.register(new EntityStringifierXmlDescriptor(this.repository));
        converter.unmarshal(visitor.toXmlString(), actualDataset);
        EntityTestUtils.assertEquals(new EntityListImpl(initialDataset), new EntityListImpl(actualDataset));
    }

}
