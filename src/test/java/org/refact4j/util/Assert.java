package org.refact4j.util;

import org.refact4j.expr.Expression;
import org.refact4j.expr.ExpressionBuilder;
import org.refact4j.functor.UnaryPredicate;

public class Assert extends org.junit.Assert {

	public static void assertThat(Object something, UnaryPredicate<Object> matcher) {
		if (!matcher.eval(something)) {
			StringBuffer message = new StringBuffer("Expected that: ");
			message.append(matcher);
			message.append(StringHelper.LINE_SEPARATOR + "but x was : ").append(something).append(
					StringHelper.LINE_SEPARATOR);
			fail(message.toString());
		}
	}

	public static Expression<Object> equalTo(Object value) {
		return ExpressionBuilder.init("x").equalTo(value).getExpression();
	}

	public static Expression<Object> notEqualTo(Object value) {
		return ExpressionBuilder.init("x").not(ExpressionBuilder.init("x").equalTo(value)).getExpression();
	}

	public static Expression<Object> between(Object min, Object max) {
		return ExpressionBuilder.init("x").between(min, max).getExpression();
	}

	public static Expression<Object> matchRegEx(String regEx) {
		return ExpressionBuilder.init("x").like(regEx).getExpression();
	}

	public static Expression<Object> not(Expression<Object> expr) {
		return ExpressionBuilder.init("x").getExpression().not(expr);
	}

	public static Expression<Object> isNull() {
		return ExpressionBuilder.init("x").isNull().getExpression();
	}

	public static Expression<Object> isNotNull() {
		return ExpressionBuilder.init("x").isNotNull().getExpression();
	}

	public static Expression<Object> and(Expression<Object> expr1, Expression<Object> expr2) {
		return expr1.and(expr2);
	}

	public static Expression<Object> or(Expression<Object> expr1, Expression<Object> expr2) {
		return expr1.or(expr2);
	}

	public static Expression<Object> instanceOf(Class<?> clazz) {
		return ExpressionBuilder.init("x").instanceOf(clazz).getExpression();
	}

}
