package org.refact4j.eom;

import org.junit.Before;
import org.junit.Test;
import org.refact4j.eom.impl.EntitySet;
import org.refact4j.eom.metamodel.DefaultEntityDescriptorRepoFactory;
import org.refact4j.eom.metamodel.EOMMetaModelRepository;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class EntityAggregateFunctorsTest {
    private final String metaModelXml = "<dataset>"
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

    private final String dataXml = "<dataset>" + "<foo id='1' field1='b' value='9'/>"
            + "<foo id='2' field1='c' value='8' >"
            + "    <bar id='11' field1='bar11_1' field2='bar11_2'/>"
            + "    <bar id='12' field1='x_bar12_1' field2='bar12_2'/>" + "</foo>"
            + "<foo id='3' field1='a' value='7'/>"
            + "<bar id='13' field1='a_bar13_1' field2='bar12_2'/>"
            + "<bar id='14' field1='bar14_1' field2='bar14_2' foo='1'/>"
            + "</dataset>";

    private EntityDescriptor fooEntityDescriptor;

    private EntityDescriptor barEntityDescriptor;

    private EntitySet entityObjects;

    @Before
    public void setUp() throws Exception {
        EntityDescriptorRepository repository = DefaultEntityDescriptorRepoFactory.init(
                EOMMetaModelRepository.get(), metaModelXml, null)
                .createEntityDescriptorRepository();
        fooEntityDescriptor = repository.getEntityDescriptor("foo");
        barEntityDescriptor = repository.getEntityDescriptor("bar");
        entityObjects = new EntitySet(EntityXmlReaderHelper.unmarshal(repository, dataXml));
    }

    @Test
    public void testMin() {
        KeyBuilder fooKeyBuilder = KeyBuilder.init(fooEntityDescriptor);
        Field fooKeyfield = fooEntityDescriptor.getField("id");
        Field fooField = fooKeyfield;
        EntityObject entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects,
                fooField);
        assertEquals(fooKeyBuilder.set(fooKeyfield, 1).getKey(), entityObject.getKey());

        fooField = fooEntityDescriptor.getField("field1");
        entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects, fooField);
        assertEquals(fooKeyBuilder.set(fooKeyfield, 3).getKey(), entityObject.getKey());

        entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects, fooEntityDescriptor,
                "value");
        assertEquals(fooKeyBuilder.set(fooKeyfield, 3).getKey(), entityObject.getKey());

        KeyBuilder barKeyBuilder = KeyBuilder.init(barEntityDescriptor);
        Field barKeyfield = barEntityDescriptor.getField("id");
        Field barField = barKeyfield;
        entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects, barField);
        assertEquals(barKeyBuilder.set(barKeyfield, 11).getKey(), entityObject.getKey());

        barField = barEntityDescriptor.getField("field1");
        entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects, barField);
        assertEquals(barKeyBuilder.set(barKeyfield, 13).getKey(), entityObject.getKey());

        entityObject = EntityAggregateFunctor.applyMinAggregateFunctor(entityObjects, barEntityDescriptor,
                "foo");
        assertEquals(barKeyBuilder.set(barKeyfield, 13).getKey(), entityObject.getKey());

    }

    @Test
    public void testMax() {
        KeyBuilder fooKeyBuilder = KeyBuilder.init(fooEntityDescriptor);
        Field fooKeyfield = fooEntityDescriptor.getField("id");
        Field fooField = fooKeyfield;
        EntityObject entityObject = EntityAggregateFunctor.applyMaxAggregateFunctor(entityObjects,
                fooField);
        assertEquals(fooKeyBuilder.set(fooKeyfield, 3).getKey(), entityObject.getKey());

        fooField = fooEntityDescriptor.getField("field1");
        entityObject = EntityAggregateFunctor.applyMaxAggregateFunctor(entityObjects, fooField);
        assertEquals(fooKeyBuilder.set(fooKeyfield, 2).getKey(), entityObject.getKey());

        entityObject = EntityAggregateFunctor.applyMaxAggregateFunctor(entityObjects, fooEntityDescriptor,
                "value");
        assertEquals(fooKeyBuilder.set(fooKeyfield, 1).getKey(), entityObject.getKey());

        KeyBuilder barKeyBuilder = KeyBuilder.init(barEntityDescriptor);
        Field barKeyfield = barEntityDescriptor.getField("id");
        Field barField = barKeyfield;
        entityObject = EntityAggregateFunctor.applyMaxAggregateFunctor(entityObjects, barField);
        assertEquals(barKeyBuilder.set(barKeyfield, 14).getKey(), entityObject.getKey());

        barField = barEntityDescriptor.getField("field1");
        entityObject = EntityAggregateFunctor.applyMaxAggregateFunctor(entityObjects, barField);
        assertEquals(barKeyBuilder.set(barKeyfield, 12).getKey(), entityObject.getKey());

    }

}
