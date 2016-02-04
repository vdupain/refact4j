package org.refact4j.function;

import org.junit.Test;
import org.refact4j.eom.EntityFieldComparator;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.expr.Expression;
import org.refact4j.expr.ExpressionBuilder;
import org.refact4j.function.commons.Between;
import org.refact4j.function.comparison.Equal;
import org.refact4j.function.comparison.Greater;
import org.refact4j.function.comparison.GreaterEqual;
import org.refact4j.function.logical.And;
import org.refact4j.model.DummyBean;
import org.refact4j.model.FooDesc;

import java.util.function.Function;

import static org.junit.Assert.*;

public class ExpressionTest {


    private void printEquivalentExpr(Expression expression1, Expression expression2) {
        System.out.println(">>" + expression1 + " SAME AS: " + expression2);
    }

    public static void assertEqualsExpr(Expression expression1, Expression expression2) {
        assertEquals(expression1.toString(), expression2.toString());
    }

    @Test
    public void testExpressions() {
        Expression expression = ExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.).get();
        Expression beanExpression = ExpressionBuilder.initBean("Value").greaterThan(10.).get();

        EntityFieldComparator<Double> fc = new EntityFieldComparator<>(new Greater(), FooDesc.VALUE, 10.);
        EntityObject dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 12.)
                .get();
        DummyBean dummy = new DummyBean();
        dummy.setValue(12.);

        boolean flag1 = fc.apply(dummyEntity);
        boolean flag2 = expression.test(dummyEntity);
        boolean flag3 = beanExpression.test(dummy);
        assertTrue(flag1);
        assertEquals(flag1, flag2);
        assertEquals(flag2, flag3);

        expression = ExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.).and(
                (ExpressionBuilder.init(FooDesc.NAME).equalTo("azerty")))
                .get();
        beanExpression = ExpressionBuilder.initBean("Value").greaterThan(10.).and(
                (ExpressionBuilder.initBean("Name").equalTo("azerty"))).get();
        EntityFieldComparator fc1 = new EntityFieldComparator(new GreaterEqual(), FooDesc.VALUE, 10.);
        EntityFieldComparator fc2 = new EntityFieldComparator(new Equal(), FooDesc.NAME, "azerty");
        java.util.function.Function<EntityObject, Boolean> func3 = new BinaryCompose(new And(), fc1, fc2);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 12.).set(
                FooDesc.NAME, "azerty").get();
        dummy = new DummyBean();
        dummy.setValue(12.);
        dummy.setName("azerty");

        flag1 = func3.apply(dummyEntity);
        flag2 = expression.test(dummyEntity);
        flag3 = beanExpression.test(dummy);
        assertEquals(flag1, flag2);
        assertEquals(flag2, flag3);

        System.out.println(">>" + expression);
        System.out.println(">>" + beanExpression);

        expression = ExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.).and(
                ExpressionBuilder.init(FooDesc.VALUE).lessThan(20.)).and(
                (ExpressionBuilder.init(FooDesc.NAME).equalTo("azerty"))).or(
                (ExpressionBuilder.init(FooDesc.NAME).equalTo("azerty"))).get();
        beanExpression = ExpressionBuilder.initBean("Value").greaterThan(10.).and(
                ExpressionBuilder.initBean("Value").lessThan(20.)).and((ExpressionBuilder.initBean("Name").equalTo("azerty")))
                .or((ExpressionBuilder.initBean("Name").equalTo("azerty"))).get();
        System.out.println(">>" + expression);
        System.out.println(">>" + beanExpression);

        expression = ExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.).and(
                (ExpressionBuilder.init(FooDesc.NAME).equalTo("azerty")).or((ExpressionBuilder
                        .init(FooDesc.NAME).equalTo("qwerty")))).get();
        beanExpression = ExpressionBuilder.initBean("Value").greaterThan(10.).and(
                (ExpressionBuilder.initBean("Name").equalTo("azerty"))
                        .or((ExpressionBuilder.initBean("Name").equalTo("qwerty")))).get();
        System.out.println(">>" + expression);
        assertTrue(expression.test(dummyEntity));
        assertTrue(beanExpression.test(dummy));

        expression = ExpressionBuilder.init().not(ExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.))
                .and(
                        (ExpressionBuilder.init(FooDesc.NAME).like("azert*")).or(ExpressionBuilder.init(
                                FooDesc.NAME).equalTo("qwerty"))).get();
        beanExpression = ExpressionBuilder.init().not(ExpressionBuilder.initBean("Value").greaterThan(10.))
                .and(
                        (ExpressionBuilder.initBean("Name").equalTo("azerty")).or(ExpressionBuilder.initBean("Name").equalTo(
                                "qwerty"))).get();
        System.out.println(">>" + expression);
        System.out.println(">>" + beanExpression);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 8.).set(
                FooDesc.NAME, "azerty").get();
        dummy = new DummyBean();
        dummy.setValue(8.);
        dummy.setName("azerty");
        assertTrue(expression.test(dummyEntity));
        assertTrue(beanExpression.test(dummy));

        Expression otherExpression = ExpressionBuilder.init().not(
                ExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.)).and(
                ExpressionBuilder.init(FooDesc.NAME).like("azert*").or(
                        ExpressionBuilder.init(FooDesc.NAME).equalTo("qwerty"))).get();
        beanExpression = ExpressionBuilder.init().not(ExpressionBuilder.initBean("Value").greaterThan(10.)).and(
                ExpressionBuilder.initBean("Name").equalTo("azerty").or(ExpressionBuilder.initBean("Name").equalTo("qwerty")))
                .get();
        assertTrue(otherExpression.test(dummyEntity));
        assertTrue(beanExpression.test(dummy));
        printEquivalentExpr(expression, otherExpression);
        assertEqualsExpr(expression, otherExpression);
    }

    @Test
    public void testExpressionsWithFunctors() {
        java.util.function.Function<EntityObject, Double> getValue = new Function<EntityObject, Double>() {
            public Double apply(EntityObject obj) {
                return obj.get(FooDesc.VALUE);
            }

            public String toString() {
                return FooDesc.VALUE.getName();
            }
        };

        Expression expression = ExpressionBuilder.init(getValue).greaterThan(10.).get();

        EntityFieldComparator<Double> fc = new EntityFieldComparator<>(new Greater(), FooDesc.VALUE, 10.);
        EntityObject dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 12.)
                .get();
        Boolean o1 = fc.apply(dummyEntity);
        Boolean o2 = expression.test(dummyEntity);
        assertTrue(o1);
        assertEquals(o1, o2);

    }

    @Test
    public void testSimpleExpression() {
        Double value100 = (double) 100;
        Expression exprGreaterOrEqual100 = ExpressionBuilder.init().greaterOrEqual(value100).get();

        assertFalse(exprGreaterOrEqual100.test(99.));
        assertTrue(exprGreaterOrEqual100.test(101.));
    }

    @Test
    public void testLikeExpression() {
        String regEx = "abcde";
        Expression exprLike = ExpressionBuilder.init().like(regEx).get();
        assertTrue(exprLike.test("abcde"));
    }

    @Test
    public void testBetweenExpression() {
        Expression expression = ExpressionBuilder.init(FooDesc.VALUE).greaterOrEqual((double) 0).and(
                ExpressionBuilder.init(FooDesc.VALUE).lessOrEqual(10.)).get();
        EntityObject dummyEntity8 = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 8.)
                .get();

        assertTrue(expression.test(dummyEntity8));
        EntityObject dummyEntity12 = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 12.)
                .get();
        assertFalse(expression.test(dummyEntity12));

        Expression betweenExpr = ExpressionBuilder.init(FooDesc.VALUE).between(0., 10.).get();
        assertTrue(betweenExpr.test(dummyEntity8));
        assertFalse(betweenExpr.test(dummyEntity12));
        printEquivalentExpr(expression, betweenExpr);

        Between between = new Between(0., 10.);
        assertTrue(between.apply(dummyEntity8.get(FooDesc.VALUE)));
        assertFalse(between.apply(dummyEntity12.get(FooDesc.VALUE)));

        DummyBean dummy8 = new DummyBean();
        dummy8.setValue(8.);
        DummyBean dummy12 = new DummyBean();
        dummy12.setValue(12.);
        between = new Between(0., 10.);
        assertTrue(between.apply(dummy8.getValue()));
        assertFalse(between.apply(dummy12.getValue()));
    }

    @Test
    public void testIsNullAndIsNotNullExpressions() {
        EntityObject dummyEntityNull = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, null)
                .get();
        Expression expression = ExpressionBuilder.init(FooDesc.VALUE).equalTo((Number) null).get();
        assertTrue(expression.test(dummyEntityNull));
        EntityObject dummyEntity12 = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 12.)
                .get();
        assertFalse(expression.test(dummyEntity12));
        Expression isNullExpression = ExpressionBuilder.init(FooDesc.VALUE).isNull().get();
        assertTrue(isNullExpression.test(dummyEntityNull));
        assertFalse(isNullExpression.test(dummyEntity12));
        printEquivalentExpr(expression, isNullExpression);

        expression = ExpressionBuilder.init().not(
                ExpressionBuilder.init(FooDesc.VALUE).equalTo((Number) null)).get();
        assertFalse(expression.test(dummyEntityNull));
        assertTrue(expression.test(dummyEntity12));
        Expression isNotNullExpression = ExpressionBuilder.init(FooDesc.VALUE).isNull().not().get();
        assertFalse(isNotNullExpression.test(dummyEntityNull));
        assertTrue(isNotNullExpression.test(dummyEntity12));
        printEquivalentExpr(expression, isNotNullExpression);

    }

    @Test
    public void testInAndNotInExpressions() {
        Expression expression = ExpressionBuilder.init(FooDesc.VALUE).equalTo((double) 0).or(
                ExpressionBuilder.init(FooDesc.VALUE).equalTo(1.)).or(
                ExpressionBuilder.init(FooDesc.VALUE).equalTo(2.)).get();
        EntityObject dummyEntity1 = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 1.)
                .get();
        EntityObject dummyEntity3 = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.VALUE, 3.)
                .get();

        assertTrue(expression.test(dummyEntity1));
        assertFalse(expression.test(dummyEntity3));

        Object[] values = new Object[]{0., 1., 2.};
        Expression inExpression = ExpressionBuilder.init(FooDesc.VALUE).in(values).get();
        assertTrue(inExpression.test(dummyEntity1));
        assertFalse(inExpression.test(dummyEntity3));
        printEquivalentExpr(expression, inExpression);

        expression = ExpressionBuilder.init().not(
                ExpressionBuilder.init(FooDesc.VALUE).equalTo(0.).or(
                        ExpressionBuilder.init(FooDesc.VALUE).equalTo(1.)).or(
                        ExpressionBuilder.init(FooDesc.VALUE).equalTo(2.))).get();
        assertTrue(expression.test(dummyEntity3));
        assertFalse(expression.test(dummyEntity1));

        Expression notInExpression = ExpressionBuilder.init(FooDesc.VALUE).in(values).not().get();
        printEquivalentExpr(expression, notInExpression);
        assertTrue(notInExpression.test(dummyEntity3));
        assertFalse(notInExpression.test(dummyEntity1));
    }


    @Test
    public void testMaxLength() {
        Expression<String> expression = ExpressionBuilder.init().maxLength(3).get();
        assertTrue(expression.test("abc"));
        assertFalse(expression.test("abcdef"));
        assertTrue(expression.test(null));
    }
}
