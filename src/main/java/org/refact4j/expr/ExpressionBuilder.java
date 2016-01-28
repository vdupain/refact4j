package org.refact4j.expr;

import org.refact4j.functor.ConstantFunction;

public final class ExpressionBuilder {
    private final Expression expression;

    private ExpressionBuilder() {
        expression = new Expression();
    }

    private ExpressionBuilder(java.util.function.Function function) {
        expression = new Expression(null, function);
    }

    private ExpressionBuilder(String property) {
        expression = new Expression(property);
    }

    public static ExpressionBuilder init() {
        return new ExpressionBuilder();
    }

    public static ExpressionBuilder init(java.util.function.Function function) {
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

    public ExpressionBuilder lessOrEqual(Object value) {
        this.expression.lessOrEqual(value);
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

    public ExpressionBuilder isNotNull() {
        this.expression.isNotNull();
        return this;
    }

    public ExpressionBuilder in(Object[] values) {
        this.expression.in(values);
        return this;
    }

    public ExpressionBuilder notIn(Object[] values) {
        this.expression.notIn(values);
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

    ExpressionBuilder equalTo(ConstantFunction constantFunctor) {
        this.expression.equalTo(constantFunctor);
        return this;
    }

    public ExpressionBuilder equalTo(Object object) {
        this.expression.equalTo(new ConstantFunction<>(object));
        return this;
    }

    public ExpressionBuilder notEqualTo(Object object) {
        this.expression.not(equalTo(new ConstantFunction<>(object)).getExpression());
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
