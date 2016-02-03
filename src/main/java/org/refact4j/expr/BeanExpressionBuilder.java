package org.refact4j.expr;

import org.refact4j.function.ConstantFunction;

public final class BeanExpressionBuilder {
    private final BeanExpression expression;

    private BeanExpressionBuilder() {
        expression = new BeanExpression();
    }

    private BeanExpressionBuilder(String property) {
        expression = new BeanExpression(property);
    }

    public static BeanExpressionBuilder init() {
        return new BeanExpressionBuilder();
    }

    public static BeanExpressionBuilder init(String property) {
        return new BeanExpressionBuilder(property);
    }

    public BeanExpressionBuilder greaterThan(Object value) {
        this.expression.greaterThan(value);
        return this;
    }

    public BeanExpressionBuilder greaterOrEqual(Object value) {
        this.expression.greaterOrEqual(value);
        return this;
    }

    public BeanExpressionBuilder lessThan(Object value) {
        this.expression.lessThan(value);
        return this;
    }

    public BeanExpressionBuilder lessOrEqual(Object value) {
        this.expression.lessOrEqual(value);
        return this;
    }

    public BeanExpressionBuilder between(Object value1, Object value2) {
        this.expression.between(value1, value2);
        return this;
    }

    public BeanExpressionBuilder isNull() {
        this.expression.isNull();
        return this;
    }

    public BeanExpressionBuilder isNotNull() {
        this.expression.isNotNull();
        return this;
    }

    public BeanExpressionBuilder in(Object[] values) {
        this.expression.in(values);
        return this;
    }

    public BeanExpressionBuilder notIn(Object[] values) {
        this.expression.notIn(values);
        return this;
    }

    public BeanExpressionBuilder and(BeanExpressionBuilder expression) {
        this.expression.and(expression.getExpression());
        return this;
    }

    public BeanExpressionBuilder or(BeanExpressionBuilder expression) {
        this.expression.or(expression.getExpression());
        return this;
    }

    public BeanExpressionBuilder not(BeanExpressionBuilder expression) {
        this.expression.not(expression.getExpression());
        return this;
    }

    private BeanExpressionBuilder equalTo(ConstantFunction constantFunctor) {
        this.expression.equalTo(constantFunctor);
        return this;
    }

    public BeanExpressionBuilder equalTo(Object object) {
        this.expression.equalTo(new ConstantFunction<>(object));
        return this;
    }

    public BeanExpressionBuilder notEqualTo(Object object) {
        this.expression.not(equalTo(new ConstantFunction<>(object)).getExpression());
        return this;
    }

    public BeanExpressionBuilder maxLength(int maxLength) {
        this.expression.maxLength(maxLength);
        return this;
    }

    public BeanExpressionBuilder minLength(int minLength) {
        this.expression.minLength(minLength);
        return this;
    }

    public BeanExpressionBuilder like(String regEx) {
        this.expression.like(regEx);
        return this;
    }

    public BeanExpression getExpression() {
        return expression;
    }

}
