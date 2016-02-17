package org.refact4j.eom.xml;

import org.junit.Before;
import org.junit.Test;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.impl.EntitySet;
import org.refact4j.eom.metamodel.DefaultEntityDescriptorRepoFactory;
import org.refact4j.eom.metamodel.EOMMetaModelRepository;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;

import static org.junit.Assert.assertEquals;


public class CompositeKeyTest {

    private final String metaModelXml = "<dataset>"
            + "<entityDescriptor name='foo'>"
            + "        <field name='id1' isKey='true' dataType='integer'/>"
            + "        <field name='id2' isKey='true' dataType='string'/>"
            + "        <field name='field1' dataType='string' defaultValue='azerty'/>"
            + "</entityDescriptor>"
            + "<entityDescriptor name='bar'>"
            + "        <field name='id' isKey='true' dataType='integer'/>"
            + "        <field name='field1' dataType='string'/>"
            + "        <field name='foo' dataType='manyToOne' target='foo' prettyName='ToOne Relation to Foo Object'/>"
            + "</entityDescriptor>"
            + "</dataset>";

    private final String dataXml = "<dataset>"
            + "    <foo id1='1' id2='1' field1='foo11' />"
            + "    <foo id1='1' id2='2' field1='foo12' >"
            + "        <bar id='1' field1='bar1' />"
            + "        <bar id='2' field1='bar2' />"
            + "    </foo>"
            + "    <foo id1='1' id2='abc'/>"
            + "    <bar id='3' field1='bar3' />"
            + "    <bar id='4' field1='bar4' foo='foo[id1=1,id2=abc]' />"
            + "</dataset>";

    private EntityDescriptorRepository repository;

    private EntitySet entityObjects;

    @Before
    public void setUp() throws Exception {
        repository = DefaultEntityDescriptorRepoFactory.init(
                EOMMetaModelRepository.get(), metaModelXml, null)
                .createEntityDescriptorRepository();
        entityObjects = new EntitySet(EntityXmlReaderHelper.unmarshal(repository, dataXml));
    }

    @Test
    public void testXml() {
        EntityDescriptor fooType = repository.getEntityDescriptor("foo");
        EntityDescriptor barType = repository.getEntityDescriptor("bar");

        Key bar4Key = KeyBuilder.init(barType).set(barType.getField("id"), 4)
                .get();
        EntityObject bar4 = entityObjects.stream()
                .filter(p -> p.getKey().equals(bar4Key))
                .findFirst().get();
        Key foo1Key = KeyBuilder.init(fooType).set(fooType.getField("id1"), 1)
                .set(fooType.getField("id2"), "abc").get();
        EntityObject foo1 = entityObjects.stream()
                .filter(p -> p.getKey().equals(foo1Key))
                .findFirst().get();
        assertEquals(bar4.get(barType.getField("foo")), foo1.getKey());
    }
}
