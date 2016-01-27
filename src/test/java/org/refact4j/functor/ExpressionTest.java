package org.refact4j.functor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.refact4j.eom.EntityExpressionBuilder;
import org.refact4j.eom.EntityFieldComparator;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.expr.BeanExpressionBuilder;
import org.refact4j.expr.Expression;
import org.refact4j.expr.ExpressionBuilder;
import org.refact4j.functor.commons.Between;
import org.refact4j.functor.commons.In;
import org.refact4j.functor.commons.NotIn;
import org.refact4j.functor.commons.Between.BetweenVisitor;
import org.refact4j.functor.commons.In.InVisitor;
import org.refact4j.functor.commons.NotIn.NotInVisitor;
import org.refact4j.functor.comparison.Equal;
import org.refact4j.functor.comparison.Greater;
import org.refact4j.functor.comparison.GreaterEqual;
import org.refact4j.functor.comparison.NotNull;
import org.refact4j.functor.comparison.Null;
import org.refact4j.functor.comparison.NotNull.NotNullVisitor;
import org.refact4j.functor.comparison.Null.NullVisitor;
import org.refact4j.functor.logical.And;
import org.refact4j.model.DummyBean;
import org.refact4j.model.FooDesc;
import org.refact4j.visitor.Visitable;

import java.util.function.Function;

public class ExpressionTest {

	@Test
	public void testExpressions() {
		Expression expression = EntityExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.).getExpression();
		Expression beanExpression = BeanExpressionBuilder.init("Value").greaterThan(10.).getExpression();

		EntityFieldComparator<Double> fc = new EntityFieldComparator<Double>(new Greater(), FooDesc.VALUE, 10.);
		EntityObject dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.)
				.getEntity();
		DummyBean dummy = new DummyBean();
		dummy.setValue(12.);

		boolean flag1 = fc.apply(dummyEntity);
		boolean flag2 = expression.apply(dummyEntity);
		boolean flag3 = beanExpression.apply(dummy);
		assertTrue(flag1);
		assertEquals(flag1, flag2);
		assertEquals(flag2, flag3);

		expression = EntityExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.).and(
				(EntityExpressionBuilder.init(FooDesc.NAME).equalTo(new ConstantFunction<String>("azerty"))))
				.getExpression();
		beanExpression = BeanExpressionBuilder.init("Value").greaterThan(10.).and(
				(BeanExpressionBuilder.init("Name").equalTo("azerty"))).getExpression();
		EntityFieldComparator fc1 = new EntityFieldComparator(new GreaterEqual(), FooDesc.VALUE, 10.);
		EntityFieldComparator fc2 = new EntityFieldComparator(new Equal(), FooDesc.NAME, "azerty");
		java.util.function.Function<EntityObject,Boolean> func3 = new BinaryCompose(new And(), fc1, fc2);
		dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.).setFieldValue(
				FooDesc.NAME, "azerty").getEntity();
		dummy = new DummyBean();
		dummy.setValue(12.);
		dummy.setName("azerty");

		flag1 = func3.apply(dummyEntity);
		flag2 = expression.apply(dummyEntity);
		flag3 = beanExpression.apply(dummy);
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
		assertTrue(expression.apply(dummyEntity));
		assertTrue(beanExpression.apply(dummy));

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
		assertTrue(expression.apply(dummyEntity));
		assertTrue(beanExpression.apply(dummy));

		Expression otherExpression = EntityExpressionBuilder.init().not(
				EntityExpressionBuilder.init(FooDesc.VALUE).greaterThan(10.)).and(
				EntityExpressionBuilder.init(FooDesc.NAME).like("azert*").or(
						EntityExpressionBuilder.init(FooDesc.NAME).equalTo("qwerty"))).getExpression();
		beanExpression = BeanExpressionBuilder.init().not(BeanExpressionBuilder.init("Value").greaterThan(10.)).and(
				BeanExpressionBuilder.init("Name").equalTo("azerty").or(BeanExpressionBuilder.init("Name").equalTo("qwerty")))
				.getExpression();
		assertTrue(otherExpression.apply(dummyEntity));
		assertTrue(beanExpression.apply(dummy));
		printEquivalentExpr(expression, otherExpression);
		assertEqualsExpr(expression, otherExpression);
	}

	@Test
	public void testExpressionsWithFunctors() {
		java.util.function.Function<EntityObject,Double> getValue = new Function<EntityObject, Double>() {
			public Double apply(EntityObject obj) {
				return obj.get(FooDesc.VALUE);
			}

			public String toString() {
				return FooDesc.VALUE.getName();
			}
		};

		Expression expression = ExpressionBuilder.init(getValue).greaterThan(10.).getExpression();

		EntityFieldComparator<Double> fc = new EntityFieldComparator<Double>(new Greater(), FooDesc.VALUE, 10.);
		EntityObject dummyEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.)
				.getEntity();
		Boolean o1 = fc.apply(dummyEntity);
		Boolean o2 = expression.apply(dummyEntity);
		assertTrue(o1);
		assertEquals(o1, o2);

	}

	@Test
	public void testSimpleExpression() {
		Double value100 = (double) 100;
		Expression exprGreaterOrEqual100 = BeanExpressionBuilder.init().greaterOrEqual(value100).getExpression();

		assertFalse(exprGreaterOrEqual100.apply(99.));
		assertTrue(exprGreaterOrEqual100.apply(101.));
	}

	@Test
	public void testLikeExpression() {
		String regEx = "abcde";
		Expression exprLike = BeanExpressionBuilder.init().like(regEx).getExpression();
		assertTrue(exprLike.apply("abcde"));
	}

	@Test
	public void testBetweenExpression() {
		Expression expression = EntityExpressionBuilder.init(FooDesc.VALUE).greaterOrEqual((double) 0).and(
				EntityExpressionBuilder.init(FooDesc.VALUE).lessOrEqual(10.)).getExpression();
		EntityObject dummyEntity8 = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 8.)
				.getEntity();

		assertTrue(expression.apply(dummyEntity8));
		EntityObject dummyEntity12 = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.)
				.getEntity();
		assertFalse(expression.apply(dummyEntity12));

		Expression betweenExpr = EntityExpressionBuilder.init(FooDesc.VALUE).between(0., 10.).getExpression();
		assertTrue(betweenExpr.apply(dummyEntity8));
		assertFalse(betweenExpr.apply(dummyEntity12));
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
		assertTrue(expression.apply(dummyEntityNull));
		EntityObject dummyEntity12 = EntityObjectBuilder.init(FooDesc.INSTANCE).setFieldValue(FooDesc.VALUE, 12.)
				.getEntity();
		assertFalse(expression.apply(dummyEntity12));
		Expression isNullExpression = EntityExpressionBuilder.init(FooDesc.VALUE).isNull().getExpression();
		assertTrue(isNullExpression.apply(dummyEntityNull));
		assertFalse(isNullExpression.apply(dummyEntity12));
		printEquivalentExpr(expression, isNullExpression);

		expression = EntityExpressionBuilder.init().not(
				EntityExpressionBuilder.init(FooDesc.VALUE).equalTo((Number) null)).getExpression();
		assertFalse(expression.apply(dummyEntityNull));
		assertTrue(expression.apply(dummyEntity12));
		Expression isNotNullExpression = EntityExpressionBuilder.init(FooDesc.VALUE).isNotNull().getExpression();
		assertFalse(isNotNullExpression.apply(dummyEntityNull));
		assertTrue(isNotNullExpression.apply(dummyEntity12));
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

		assertTrue(expression.apply(dummyEntity1));
		assertFalse(expression.apply(dummyEntity3));

		Object[] values = new Object[] { 0., 1., 2. };
		Expression inExpression = EntityExpressionBuilder.init(FooDesc.VALUE).in(values).getExpression();
		assertTrue(inExpression.apply(dummyEntity1));
		assertFalse(inExpression.apply(dummyEntity3));
		printEquivalentExpr(expression, inExpression);

		Expression inExpression2 = EntityExpressionBuilder.init(FooDesc.VALUE).in(values).getExpression();
		assertTrue(inExpression2.apply(dummyEntity1));
		assertFalse(inExpression2.apply(dummyEntity3));
		printEquivalentExpr(expression, inExpression2);

		expression = EntityExpressionBuilder.init().not(
				EntityExpressionBuilder.init(FooDesc.VALUE).equalTo(0.).or(
						EntityExpressionBuilder.init(FooDesc.VALUE).equalTo(1.)).or(
						EntityExpressionBuilder.init(FooDesc.VALUE).equalTo(2.))).getExpression();
		assertTrue(expression.apply(dummyEntity3));
		assertFalse(expression.apply(dummyEntity1));

		Expression notInExpression = EntityExpressionBuilder.init(FooDesc.VALUE).notIn(values).getExpression();
		assertTrue(notInExpression.apply(dummyEntity3));
		assertFalse(notInExpression.apply(dummyEntity1));
		printEquivalentExpr(expression, notInExpression);
	}

	@Test
	public void testMaxLength() {
		Expression<String> expression = ExpressionBuilder.init().maxLength(3).getExpression();
		assertTrue(expression.apply("abc"));
		assertFalse(expression.apply("abcdef"));
		assertTrue(expression.apply(null));
	}

	public static void printEquivalentExpr(java.util.function.Function functor1, java.util.function.Function functor2) {
		System.out.println(">>" + functor1 + " SAME AS: " + functor2);
	}

	public static void assertEqualsExpr(Expression expression1, Expression expression2) {
		assertEquals(expression1.toString(), expression2.toString());
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
