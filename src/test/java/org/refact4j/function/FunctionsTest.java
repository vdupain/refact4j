package org.refact4j.function;

import org.junit.Test;
import org.refact4j.eom.EntityFieldComparator;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.FieldValuePredicate;
import org.refact4j.function.arithmetic.Multiplies;
import org.refact4j.function.comparison.Equal;
import org.refact4j.function.comparison.Greater;
import org.refact4j.function.comparison.GreaterEqual;
import org.refact4j.function.comparison.LessEqual;
import org.refact4j.function.logical.And;
import org.refact4j.function.logical.Not;
import org.refact4j.model.DummyBean;
import org.refact4j.model.FooDesc;
import org.refact4j.util.PrettyPrinter;

import java.util.function.Function;

import static org.junit.Assert.*;
import static org.refact4j.function.Functions.*;

public class FunctionsTest {

    @Test
    public void testConstantArgUnaryFunction() {
        double discountRate = 0.2;
        double taxRate = 0.20;

        Function<Number, Number> discountedPrice = bind1st(new Multiplies(),
                1 - discountRate);
        Function<Number, Number> taxedPrice = bind1st(new Multiplies(), 1 + taxRate);

        Number value = 100.;
        value = discountedPrice.apply(value);
        assertEquals(value, 80.);
        value = taxedPrice.apply(value);
        assertEquals(value, 96.);

        value = 100.;
        value = discountedPrice.apply(taxedPrice.apply(value));
        assertEquals(value, 96.);

        UnaryCompose<Number, Number, Number> calcPrice = new UnaryCompose<>(
                discountedPrice, taxedPrice);
        value = 100.;
        value = calcPrice.apply(value);
        assertEquals(value, 96.);

        Function<Number, Number> functor = bind2nd(new Multiplies(), 1 + taxRate);
        value = 100.;
        value = discountedPrice.apply(value);
        assertEquals(value, 80.);
        value = functor.apply(value);
        assertEquals(value, 96.);
    }

    @Test
    public void testCompositeFunction() {
        BinaryCompose<Boolean, Boolean, Boolean, Boolean> func3 = new BinaryCompose<>(
                new And(), new Not(), new Not());
        assertFalse(func3.apply(true));

        java.util.function.Function<org.refact4j.model.DummyBean, Boolean> predicate = new BinaryCompose<>(
                new Greater(), constant(1.), constant(2.));
        assertFalse(predicate.apply(null));

        predicate = new BinaryCompose<>(new Greater(),
                new GetFieldFunction("Value"), constant(100.));
        DummyBean dummy = new DummyBean();
        dummy.setValue(99.);
        assertFalse(predicate.apply(dummy));

        try {
            new GetFieldFunction("???").apply(dummy);
            fail("NoSuchMethodException Expected");
        } catch (Exception e) {
            assertEquals("java.lang.NoSuchMethodException: org.refact4j.model.DummyBean.get???()", e
                    .getMessage());
        }

        PrettyPrinter printer = new PrettyPrinter();
        EntityFieldComparator<Double> fieldComparator = new EntityFieldComparator<>(new Greater(), FooDesc.VALUE,
                100.);
        EntityObject dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE,
                99.).getEntity();
        assertFalse(fieldComparator.test(dummyEntity));

        fieldComparator = new EntityFieldComparator<>(new GreaterEqual(), FooDesc.VALUE, 12.);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.).getEntity();
        assertTrue(fieldComparator.test(dummyEntity));

        UnaryPredicate<EntityObject> dummyWith100AsValue = new FieldValuePredicate<>(FooDesc.VALUE, 100.);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 100.)
                .getEntity();
        assertTrue(dummyWith100AsValue.apply(dummyEntity));
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 123.)
                .getEntity();
        assertFalse(dummyWith100AsValue.apply(dummyEntity));

        System.out.println(">>" + printer.toString(dummyWith100AsValue));

        EntityFieldComparator<Double> fc1 = new EntityFieldComparator<>(new GreaterEqual(), FooDesc.VALUE, 10.);
        EntityFieldComparator<?> fc2 = new EntityFieldComparator<>(new LessEqual(), FooDesc.VALUE, 15.);

        BinaryCompose<Boolean, Boolean, EntityObject, Boolean> func4 = new BinaryCompose<>(
                new And(), fc1, fc2);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.).getEntity();
        assertTrue(func4.apply(dummyEntity));
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 9.).getEntity();
        assertFalse(func4.apply(dummyEntity));

        fc1 = new EntityFieldComparator<>(new GreaterEqual(), FooDesc.VALUE, 10.);
        fc2 = new EntityFieldComparator<>(new Equal(), FooDesc.NAME, "azerty");
        func4 = new BinaryCompose<>(new And(), fc1, fc2);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.)
                .setFieldValue(FooDesc.NAME, "azerty").getEntity();
        assertTrue(func4.apply(dummyEntity));
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 9.)
                .setFieldValue(FooDesc.NAME, "azerty").getEntity();
        assertFalse(func4.apply(dummyEntity));
    }

}
