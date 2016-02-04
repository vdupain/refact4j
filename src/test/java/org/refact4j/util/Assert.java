package org.refact4j.util;

import org.refact4j.expr.Expression;
import org.refact4j.expr.ExpressionBuilder;

import java.util.function.Predicate;

public class Assert extends org.junit.Assert {

    public static void assertThat(Object something, Predicate<Object> matcher) {
        if (!matcher.test(something)) {
            StringBuilder message = new StringBuilder("Expected that: ");
            message.append(matcher);
            message.append(StringHelper.LINE_SEPARATOR + "but value was : ").append(something).append(
                    StringHelper.LINE_SEPARATOR);
            fail(message.toString());
        }
    }

    public static Expression<Object> equalTo(Object value) {
        return ExpressionBuilder.init("value").equalTo(value).get();
    }

    public static Expression<Object> notEqualTo(Object value) {
        return ExpressionBuilder.init("value").not(ExpressionBuilder.init("value").equalTo(value)).get();
    }

    public static Expression<Object> between(Object min, Object max) {
        return ExpressionBuilder.init("value").between(min, max).get();
    }

    public static Expression<Object> matchRegEx(String regEx) {
        return ExpressionBuilder.init("value").like(regEx).get();
    }

    public static Expression<Object> not(Expression<Object> expr) {
        return ExpressionBuilder.init("value").get().not(expr);
    }

    public static Expression<Object> isNull() {
        return ExpressionBuilder.init("value").isNull().get();
    }

    public static Expression<Object> and(Expression<Object> expr1, Expression<Object> expr2) {
        return expr1.and(expr2);
    }

    public static Expression<Object> or(Expression<Object> expr1, Expression<Object> expr2) {
        return expr1.or(expr2);
    }

    public static Expression<Object> instanceOf(Class<?> clazz) {
        return ExpressionBuilder.init("value").instanceOf(clazz).get();
    }

}
