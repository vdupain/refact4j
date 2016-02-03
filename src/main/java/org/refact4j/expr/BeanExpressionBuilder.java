package org.refact4j.expr;

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

    public BeanExpressionBuilder between(Object value1, Object value2) {
        this.expression.between(value1, value2);
        return this;
    }


    public BeanExpressionBuilder in(Object[] values) {
        this.expression.in(values);
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

    public BeanExpressionBuilder equalTo(Object object) {
        this.expression.equalTo(object);
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
