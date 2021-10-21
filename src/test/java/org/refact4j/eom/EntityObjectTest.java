package org.refact4j.eom;

import org.junit.Before;
import org.junit.Test;
import org.refact4j.eom.impl.EntityObjectDecorator;
import org.refact4j.eom.impl.EntityObjectImpl;
import org.refact4j.eom.model.*;
import org.refact4j.model.BarDesc;
import org.refact4j.model.DummyRepository;
import org.refact4j.model.FooDesc;
import org.refact4j.util.EqualsHashCodeAssert;

import java.util.*;

import static org.junit.Assert.*;

public class EntityObjectTest {
    private final Integer id99 = 99;
    private final Date date = new Date();
    private final Double value = 1.23;
    private final Boolean flag = false;
    private EntityObject foo;
    private EntityObject fooDecorated;
    private EntityObject bar;
    private Integer id = 1;
    private String name = "foo";

    @Before
    public void setUp() throws Exception {
        bar = EntityObjectBuilder.initWithDefaultValues(BarDesc.INSTANCE).set(BarDesc.ID, id99).set(BarDesc.NAME, name)
                .set(BarDesc.VALUE, value).get();
        EntityObjectBuilder dummyEntityBuilder = EntityObjectBuilder.init(FooDesc.INSTANCE);
        foo = dummyEntityBuilder.set(FooDesc.ID, id).set(FooDesc.NAME, name).set(FooDesc.VALUE, value).set(
                FooDesc.BEGIN_DATE, date).set(FooDesc.FLAG, flag).set(FooDesc.BAR, bar).get();
        fooDecorated = new EntityObjectDecorator(foo);
    }

    @Test
    public void testCreateDummyEntity() {
        testSame(foo);
        testSame(fooDecorated);
    }

    private void testSame(EntityObject entityObject) {
        assertSame(id, entityObject.get(FooDesc.ID));
        assertSame(name, entityObject.get(FooDesc.NAME));
        assertSame(value, entityObject.get(FooDesc.VALUE));
        assertSame(flag, entityObject.get(FooDesc.FLAG));
        Key key = KeyBuilder.init(BarDesc.INSTANCE).set(BarDesc.ID, id99).get();
        assertEquals(key, entityObject.get(FooDesc.BAR));
    }

    @Test
    public void testEquality() {
        testEquality(foo);
        testEquality(fooDecorated);
        EqualsHashCodeAssert.assertEqualsHashCodeCoherent(foo, fooDecorated);
    }

    private void testEquality(EntityObject entityObject) {
        EqualsHashCodeAssert.assertEqualsIsReflexive(entityObject);
        assertNotNull(entityObject);

        assertTrue(entityObject.equals(entityObject));
        assertFalse(entityObject.equals(bar));
        assertFalse(entityObject.equals(""));
        assertNotNull(entityObject);
    }

    @Test
    public void testCheckField() {
        try {
            foo = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, id).set(FooDesc.NAME, name).set(
                    BarDesc.VALUE, value).set(FooDesc.BEGIN_DATE, date).set(FooDesc.BAR, bar).get();
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Object type 'Foo' does not contain field 'Bar.value'", e.getMessage());
        }

        FooDesc.NAME.checkValue("azerty");

        try {
            FooDesc.NAME.checkValue(0.);
            fail("Excepted Exception");
        } catch (Exception e) {
            assertEquals("Value must be instance of java.lang.String but was java.lang.Double", e.getMessage());
        }

    }

    @Test
    public void testFields() {
        FieldVisitor fieldVisitor = new FieldVisitor() {

            public void visitField(Field field) {
            }

            public void visitIntegerField(IntegerField integerField) {
                assertEquals(FooDesc.ID, integerField);
            }

            public void visitDoubleField(DoubleField doubleField) {
                assertEquals(FooDesc.VALUE, doubleField);
            }

            public void visitStringField(StringField stringField) {
                assertEquals(FooDesc.NAME, stringField);
                assertEquals("The Name", stringField.getPrettyName());
            }

            public void visitDateField(DateField dateField) {
            }

            public void visitBooleanField(BooleanField booleanField) {
                assertEquals(FooDesc.FLAG, booleanField);
            }

            public void visitManyToOneRelationField(ManyToOneRelationField manyToOneRelationField) {
                assertTrue(manyToOneRelationField == FooDesc.BAR || manyToOneRelationField == FooDesc.BAR_2);
            }

            public void visitOneToManyRelationField(OneToManyRelationField oneToManyRelationField) {
            }

            public void visitOneToOneRelationField(OneToOneRelationField oneToOneRelationField) {
            }
        };
        for (Object o : foo.getEntityDescriptor().getFields()) {
            Field field = (Field) o;
            field.accept(fieldVisitor);
        }

        assertTrue(foo.getEntityDescriptor().getKeyFields().contains(FooDesc.ID));
        assertTrue(fooDecorated.getEntityDescriptor().getKeyFields().contains(FooDesc.ID));

        Collection<RelationField> relationFields = FooDesc.INSTANCE.getRelationFields();
        relationFields.addAll(BarDesc.INSTANCE.getRelationFields());
        for (Field field : relationFields) {
            assertTrue(field instanceof RelationField);
        }
        Collection<DataField> dataFields = FooDesc.INSTANCE.getAttributeFields();
        dataFields.addAll(BarDesc.INSTANCE.getAttributeFields());
        for (Field field : dataFields) {
            assertTrue(field instanceof DataField);
        }
    }

    @Test
    public void testEntityStringifier() {
        String sDate = EntityUtils.formatDate(new Date());
        assertEquals("<Foo bar=\"99\" bar2=\"null\" beginDate=\"" + sDate
                        + "\" endDate=\"null\" flag=\"false\" id=\"1\" name=\"foo\" timestampDate=\"null\" value=\"1.23\"/>",
                foo.toString());
        assertEquals(foo.toString(), fooDecorated.toString());
        assertEquals(foo.toXmlString(), fooDecorated.toXmlString());
        ((EntityObjectImpl) foo).setStringifier(EntityStringifier.DEFAULT);
        assertEquals("1", foo.toString());
    }


    @Test
    public void testClone() throws CloneNotSupportedException {
        testClone(foo);
        testClone(fooDecorated);
    }

    private void testClone(EntityObject entityObject) throws CloneNotSupportedException {
        assertTrue(entityObject.clone() != entityObject);
        assertTrue(entityObject.clone().getClass() == entityObject.getClass());
        assertTrue(entityObject.clone().equals(entityObject));
    }

    @Test
    public void testUnknownEntityDescriptor() {
        assertNull(DummyRepository.get().getEntityDescriptor("???"));
    }

    @Test
    public void testDecoratedEntity() {
        fooDecorated.set(FooDesc.ID, fooDecorated.get(FooDesc.ID));
        fooDecorated.set(FooDesc.NAME, fooDecorated.get(FooDesc.NAME));
        fooDecorated.set(FooDesc.BAR, fooDecorated.get(FooDesc.BAR));
        fooDecorated.checkValues();
        assertEquals(fooDecorated.get(FooDesc.ID), foo.get(FooDesc.ID));
        assertEquals(fooDecorated.get(FooDesc.NAME), foo.get(FooDesc.NAME));
    }

}
