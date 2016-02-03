package org.refact4j.function;

import org.junit.Test;
import org.refact4j.eom.EntityExpressionBuilder;
import org.refact4j.eom.EntityFieldComparator;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.expr.BeanExpressionBuilder;
import org.refact4j.expr.Expression;
import org.refact4j.expr.ExpressionBuilder;
import org.refact4j.function.commons.Between;
import org.refact4j.function.commons.Between.BetweenVisitor;
import org.refact4j.function.commons.In;
import org.refact4j.function.commons.In.InVisitor;
import org.refact4j.function.commons.NotIn;
import org.refact4j.function.commons.NotIn.NotInVisitor;
import org.refact4j.function.comparison.*;
import org.refact4j.function.comparison.NotNull.NotNullVisitor;
import org.refact4j.function.comparison.Null.NullVisitor;
import org.refact4j.function.logical.And;
import org.refact4j.model.DummyBean;
import org.refact4j.model.FooDesc;
import org.refact4j.visitor.Visitable;

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
        Expression expression = EntityExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.).getExpression();
        Expression beanExpression = BeanExpressionBuilder.init("Value").greaterThan(10.).getExpression();

        EntityFieldComparator<Double> fc = new EntityFieldComparator<>(new Greater(), FooDesc.VALUE, 10.);
        EntityObject dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.)
                .getEntity();
        DummyBean dummy = new DummyBean();
        dummy.setValue(12.);

        boolean flag1 = fc.apply(dummyEntity);
        boolean flag2 = expression.test(dummyEntity);
        boolean flag3 = beanExpression.test(dummy);
        assertTrue(flag1);
        assertEquals(flag1, flag2);
        assertEquals(flag2, flag3);

        expression = EntityExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.).and(
                (EntityExpressionBuilder.init(FooDesc.NAME).equalTo(new ConstantFunction<>("azerty"))))
                .getExpression();
        beanExpression = BeanExpressionBuilder.init("Value").greaterThan(10.).and(
                (BeanExpressionBuilder.init("Name").equalTo("azerty"))).getExpression();
        EntityFieldComparator fc1 = new EntityFieldComparator(new GreaterEqual(), FooDesc.VALUE, 10.);
        EntityFieldComparator fc2 = new EntityFieldComparator(new Equal(), FooDesc.NAME, "azerty");
        java.util.function.Function<EntityObject, Boolean> func3 = new BinaryCompose(new And(), fc1, fc2);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.).setFieldValue(
                FooDesc.NAME, "azerty").getEntity();
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

        expression = EntityExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.).and(
                EntityExpressionBuilder.init(FooDesc.VALUE).lessThan(20.)).and(
                (EntityExpressionBuilder.init(FooDesc.NAME).equalTo("azerty"))).or(
                (EntityExpressionBuilder.init(FooDesc.NAME).equalTo("azerty"))).getExpression();
        beanExpression = BeanExpressionBuilder.init("Value").greaterThan(10.).and(
                BeanExpressionBuilder.init("Value").lessThan(20.)).and((BeanExpressionBuilder.init("Name").equalTo("azerty")))
                .or((BeanExpressionBuilder.init("Name").equalTo("azerty"))).getExpression();
        System.out.println(">>" + expression);
        System.out.println(">>" + beanExpression);

        expression = EntityExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.).and(
                (EntityExpressionBuilder.init(FooDesc.NAME).equalTo("azerty")).or((EntityExpressionBuilder
                        .init(FooDesc.NAME).equalTo("qwerty")))).getExpression();
        beanExpression = BeanExpressionBuilder.init("Value").greaterThan(10.).and(
                (BeanExpressionBuilder.init("Name").equalTo("azerty"))
                        .or((BeanExpressionBuilder.init("Name").equalTo("qwerty")))).getExpression();
        System.out.println(">>" + expression);
        assertTrue(expression.test(dummyEntity));
        assertTrue(beanExpression.test(dummy));

        expression = EntityExpressionBuilder.init().not(EntityExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.))
                .and(
                        (EntityExpressionBuilder.init(FooDesc.NAME).like("azert*")).or(EntityExpressionBuilder.init(
                                FooDesc.NAME).equalTo("qwerty"))).getExpression();
        beanExpression = BeanExpressionBuilder.init().not(BeanExpressionBuilder.init("Value").greaterThan(10.))
                .and(
                        (BeanExpressionBuilder.init("Name").equalTo("azerty")).or(BeanExpressionBuilder.init("Name").equalTo(
                                "qwerty"))).getExpression();
        System.out.println(">>" + expression);
        System.out.println(">>" + beanExpression);
        dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 8.).setFieldValue(
                FooDesc.NAME, "azerty").getEntity();
        dummy = new DummyBean();
        dummy.setValue(8.);
        dummy.setName("azerty");
        assertTrue(expression.test(dummyEntity));
        assertTrue(beanExpression.test(dummy));

        Expression otherExpression = EntityExpressionBuilder.init().not(
                EntityExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.)).and(
                EntityExpressionBuilder.init(FooDesc.NAME).like("azert*").or(
                        EntityExpressionBuilder.init(FooDesc.NAME).equalTo("qwerty"))).getExpression();
        beanExpression = BeanExpressionBuilder.init().not(BeanExpressionBuilder.init("Value").greaterThan(10.)).and(
                BeanExpressionBuilder.init("Name").equalTo("azerty").or(BeanExpressionBuilder.init("Name").equalTo("qwerty")))
                .getExpression();
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

        Expression expression = ExpressionBuilder.init(getValue).greaterThan(10.).getExpression();

        EntityFieldComparator<Double> fc = new EntityFieldComparator<>(new Greater(), FooDesc.VALUE, 10.);
        EntityObject dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.)
                .getEntity();
        Boolean o1 = fc.apply(dummyEntity);
        Boolean o2 = expression.test(dummyEntity);
        assertTrue(o1);
        assertEquals(o1, o2);

    }

    @Test
    public void testSimpleExpression() {
        Double value100 = (double) 100;
        Expression exprGreaterOrEqual100 = BeanExpressionBuilder.init().greaterOrEqual(value100).getExpression();

        assertFalse(exprGreaterOrEqual100.test(99.));
        assertTrue(exprGreaterOrEqual100.test(101.));
    }

    @Test
    public void testLikeExpression() {
        String regEx = "abcde";
        Expression exprLike = BeanExpressionBuilder.init().like(regEx).getExpression();
        assertTrue(exprLike.test("abcde"));
    }

    @Test
    public void testBetweenExpression() {
        Expression expression = EntityExpressionBuilder.init(FooDesc.VALUE).greaterOrEqual((double) 0).and(
                EntityExpressionBuilder.init(FooDesc.VALUE).lessOrEqual(10.)).getExpression();
        EntityObject dummyEntity8 = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 8.)
                .getEntity();

        assertTrue(expression.test(dummyEntity8));
        EntityObject dummyEntity12 = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.)
                .getEntity();
        assertFalse(expression.test(dummyEntity12));

        Expression betweenExpr = EntityExpressionBuilder.init(FooDesc.VALUE).between(0., 10.).getExpression();
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
        EntityObject dummyEntityNull = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, null)
                .getEntity();
        Expression expression = EntityExpressionBuilder.init(FooDesc.VALUE).equalTo((Number) null).getExpression();
        assertTrue(expression.test(dummyEntityNull));
        EntityObject dummyEntity12 = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.)
                .getEntity();
        assertFalse(expression.test(dummyEntity12));
        Expression isNullExpression = EntityExpressionBuilder.init(FooDesc.VALUE).isNull().getExpression();
        assertTrue(isNullExpression.test(dummyEntityNull));
        assertFalse(isNullExpression.test(dummyEntity12));
        printEquivalentExpr(expression, isNullExpression);

        expression = EntityExpressionBuilder.init().not(
                EntityExpressionBuilder.init(FooDesc.VALUE).equalTo((Number) null)).getExpression();
        assertFalse(expression.test(dummyEntityNull));
        assertTrue(expression.test(dummyEntity12));
        Expression isNotNullExpression = EntityExpressionBuilder.init(FooDesc.VALUE).isNotNull().getExpression();
        assertFalse(isNotNullExpression.test(dummyEntityNull));
        assertTrue(isNotNullExpression.test(dummyEntity12));
        printEquivalentExpr(expression, isNotNullExpression);

    }

    @Test
    public void testInAndNotInExpressions() {

        Expression expression = EntityExpressionBuilder.init(FooDesc.VALUE).equalTo((double) 0).or(
                EntityExpressionBuilder.init(FooDesc.VALUE).equalTo(1.)).or(
                EntityExpressionBuilder.init(FooDesc.VALUE).equalTo(2.)).getExpression();
        EntityObject dummyEntity1 = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 1.)
                .getEntity();
        EntityObject dummyEntity3 = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 3.)
                .getEntity();

        assertTrue(expression.test(dummyEntity1));
        assertFalse(expression.test(dummyEntity3));

        Object[] values = new Object[]{0., 1., 2.};
        Expression inExpression = EntityExpressionBuilder.init(FooDesc.VALUE).in(values).getExpression();
        assertTrue(inExpression.test(dummyEntity1));
        assertFalse(inExpression.test(dummyEntity3));
        printEquivalentExpr(expression, inExpression);

        Expression inExpression2 = EntityExpressionBuilder.init(FooDesc.VALUE).in(values).getExpression();
        assertTrue(inExpression2.test(dummyEntity1));
        assertFalse(inExpression2.test(dummyEntity3));
        printEquivalentExpr(expression, inExpression2);

        expression = EntityExpressionBuilder.init().not(
                EntityExpressionBuilder.init(FooDesc.VALUE).equalTo(0.).or(
                        EntityExpressionBuilder.init(FooDesc.VALUE).equalTo(1.)).or(
                        EntityExpressionBuilder.init(FooDesc.VALUE).equalTo(2.))).getExpression();
        assertTrue(expression.test(dummyEntity3));
        assertFalse(expression.test(dummyEntity1));

        Expression notInExpression = EntityExpressionBuilder.init(FooDesc.VALUE).notIn(values).getExpression();
        assertTrue(notInExpression.test(dummyEntity3));
        assertFalse(notInExpression.test(dummyEntity1));
        printEquivalentExpr(expression, notInExpression);
    }


    @Test
    public void testMaxLength() {
        Expression<String> expression = ExpressionBuilder.init().maxLength(3).getExpression();
        assertTrue(expression.test("abc"));
        assertFalse(expression.test("abcdef"));
        assertTrue(expression.test(null));
    }

    class ExpressionVisitor implements BetweenVisitor, NullVisitor, NotNullVisitor, InVisitor, NotInVisitor {

        public void visitBetween(Between between) {
        }

        public void visit(Visitable visitable) {
        }

        public void visitNull(Null nul) {
        }

        public void visitNotNull(NotNull notNull) {
        }

        public void visitIn(In in) {
        }

        public void visitNotIn(NotIn notIn) {
        }

    }

}