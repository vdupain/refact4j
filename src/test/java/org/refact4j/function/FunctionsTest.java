package org.refact4j.function;

import org.junit.Test;
import org.refact4j.eom.EntityFieldComparator;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.FieldValuePredicate;
import org.refact4j.function.comparison.LessEqual;
import org.refact4j.function.logical.And;
import org.refact4j.function.logical.Not;
import org.refact4j.model.DummyBean;
import org.refact4j.model.FooDesc;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.*;
import static org.refact4j.function.Functions.*;

public class FunctionsTest {

    @Test
    public void testConstantArgUnaryFunction() {
        double discountRate = 0.2;
        double taxRate = 0.20;

        Function<Double, Double> discountedPrice = bind1st((o1, o2) -> o1*o2,
                1 - discountRate);
        Function<Double, Double> taxedPrice = bind1st((o1, o2) -> o1*o2, 1 + taxRate);

        double value = 100.;
        value = discountedPrice.apply(value);
        assertEquals(value, 80., 0);
        value = taxedPrice.apply(value);
        assertEquals(value, 96., 0);

        value = 100.;
        value = discountedPrice.apply(taxedPrice.apply(value));
        assertEquals(value, 96., 0);

        ComposeFunction<Double, Double, Double> calcPrice = new ComposeFunction<>(
                discountedPrice, taxedPrice);
        value = 100.;
        value = calcPrice.apply(value);
        assertEquals(value, 96., 0);

        value = 100.;
        value = discountedPrice.compose(taxedPrice).apply(value);
        assertEquals(value, 96., 0);

        Function<Double, Double> functor = bind2nd((o1, o2) -> o1*o2, 1 + taxRate);
        value = 100.;
        value = discountedPrice.apply(value);
        assertEquals(value, 80., 0);
        value = functor.apply(value);
        assertEquals(value, 96., 0);
    }

    @Test
    public void testCompositeFunction() {
        BinaryCompose<Boolean, Boolean, Boolean, Boolean> func3 = new BinaryCompose<>(
                new And(), new Not(), new Not());

        assertFalse(func3.apply(true));


        BiFunction<Double, Double, Boolean> greater = (o1, o2) -> o1 > o2;
        BiFunction<Double, Double, Boolean> greaterEquals = (o1, o2) -> o1 >= o2;

        java.util.function.Function<org.refact4j.model.DummyBean, Boolean> predicate = new BinaryCompose<>(
                greater, constant(1.), constant(2.));
        assertFalse(predicate.apply(null));

        predicate = new BinaryCompose<>(greater,
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

        EntityFieldComparator<Double> fieldComparator = new EntityFieldComparator<>(greater, FooDesc.VALUE,
                100.);
        EntityObject dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE,
                99.).get();
        assertFalse(fieldComparator.test(dummyEntity));

        fieldComparator = new EntityFieldComparator<>(greaterEquals, FooDesc.VALUE, 12.);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 12.).get();
        assertTrue(fieldComparator.test(dummyEntity));

        UnaryPredicate<EntityObject> dummyWith100AsValue = new FieldValuePredicate<>(FooDesc.VALUE, 100.);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 100.)
                .get();
        assertTrue(dummyWith100AsValue.apply(dummyEntity));
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 123.)
                .get();
        assertFalse(dummyWith100AsValue.apply(dummyEntity));

        EntityFieldComparator<Double> fc1 = new EntityFieldComparator<>(greaterEquals, FooDesc.VALUE, 10.);
        EntityFieldComparator<?> fc2 = new EntityFieldComparator<>(new LessEqual(), FooDesc.VALUE, 15.);

        BinaryCompose<Boolean, Boolean, EntityObject, Boolean> func4 = new BinaryCompose<>(
                new And(), fc1, fc2);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 12.).get();
        assertTrue(func4.apply(dummyEntity));
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 9.).get();
        assertFalse(func4.apply(dummyEntity));

        fc1 = new EntityFieldComparator<>(greaterEquals, FooDesc.VALUE, 10.);
        BiFunction<String, String, Boolean> isEquals = (o1, o2) -> o1.equals(o2);

        fc2 = new EntityFieldComparator<>(isEquals, FooDesc.NAME, "azerty");
        func4 = new BinaryCompose<>(new And(), fc1, fc2);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 12.)
                .set(FooDesc.NAME, "azerty").get();
        assertTrue(func4.apply(dummyEntity));
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 9.)
                .set(FooDesc.NAME, "azerty").get();
        assertFalse(func4.apply(dummyEntity));
    }

}
