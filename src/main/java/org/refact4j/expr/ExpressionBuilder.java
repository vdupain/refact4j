package org.refact4j.expr;

import java.util.function.Function;

public class ExpressionBuilder {
    protected Expression expression;

    public ExpressionBuilder() {
        expression = new Expression();
    }

    private ExpressionBuilder(Function function) {
        expression = new Expression(null, function);
    }

    private ExpressionBuilder(String property) {
        expression = new Expression(property);
    }

    public static ExpressionBuilder init() {
        return new ExpressionBuilder();
    }

    public static ExpressionBuilder init(Function function) {
        return new ExpressionBuilder(function);
    }

    public static ExpressionBuilder init(String property) {
        return new ExpressionBuilder(property);
    }

    public ExpressionBuilder greaterThan(Object value) {
        this.expression.greaterThan(value);
        return this;
    }

    public ExpressionBuilder greaterOrEqual(Object value) {
        this.expression.greaterOrEqual(value);
        return this;
    }

    public ExpressionBuilder lessThan(Object value) {
        this.expression.lessThan(value);
        return this;
    }


    public ExpressionBuilder between(Object value1, Object value2) {
        this.expression.between(value1, value2);
        return this;
    }

    public ExpressionBuilder isNull() {
        this.expression.isNull();
        return this;
    }

    public ExpressionBuilder in(Object[] values) {
        this.expression.in(values);
        return this;
    }

    public ExpressionBuilder and(ExpressionBuilder expression) {
        this.expression.and(expression.getExpression());
        return this;
    }

    public ExpressionBuilder or(ExpressionBuilder expression) {
        this.expression.or(expression.getExpression());
        return this;
    }

    public ExpressionBuilder not(ExpressionBuilder expression) {
        this.expression.not(expression.getExpression());
        return this;
    }

    public ExpressionBuilder equalTo(Object object) {
        this.expression.equalTo(object);
        return this;
    }

    public ExpressionBuilder maxLength(int maxLength) {
        this.expression.maxLength(maxLength);
        return this;
    }

    public ExpressionBuilder minLength(int minLength) {
        this.expression.minLength(minLength);
        return this;
    }

    public ExpressionBuilder like(String regEx) {
        this.expression.like(regEx);
        return this;
    }

    public ExpressionBuilder instanceOf(Class<?> clazz) {
        this.expression.instanceOf(clazz);
        return this;
    }

    public Expression getExpression() {
        return expression;
    }


}
