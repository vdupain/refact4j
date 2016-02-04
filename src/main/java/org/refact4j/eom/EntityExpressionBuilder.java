package org.refact4j.eom;

import org.refact4j.eom.model.Field;

public final class EntityExpressionBuilder {
    private final EntityExpression expression;

    private EntityExpressionBuilder() {
        expression = new EntityExpression();
    }

    private EntityExpressionBuilder(Field field) {
        expression = new EntityExpression(field);
    }

    private EntityExpressionBuilder(String fieldName) {
        expression = new EntityExpression(fieldName);
    }

    public static EntityExpressionBuilder init() {
        return new EntityExpressionBuilder();
    }

    public static EntityExpressionBuilder init(Field field) {
        return new EntityExpressionBuilder(field);
    }

    public static EntityExpressionBuilder init(String fieldName) {
        return new EntityExpressionBuilder(fieldName);
    }

    public EntityExpressionBuilder greaterThan(Object value) {
        this.expression.greaterThan(value);
        return this;
    }

    public EntityExpressionBuilder greaterOrEqual(Object value) {
        this.expression.greaterOrEqual(value);
        return this;
    }

    public EntityExpressionBuilder lessThan(Object value) {
        this.expression.lessThan(value);
        return this;
    }

    public EntityExpressionBuilder lessOrEqual(Object value) {
        this.expression.lessOrEqual(value);
        return this;
    }

    public EntityExpressionBuilder between(Object value1, Object value2) {
        this.expression.between(value1, value2);
        return this;
    }

    public EntityExpressionBuilder isNull() {
        this.expression.isNull();
        return this;
    }

    public EntityExpressionBuilder in(Object[] values) {
        this.expression.in(values);
        return this;
    }

    public EntityExpressionBuilder and(EntityExpressionBuilder expression) {
        this.expression.and(expression.getExpression());
        return this;
    }

    public EntityExpressionBuilder or(EntityExpressionBuilder expression) {
        this.expression.or(expression.getExpression());
        return this;
    }


    public EntityExpressionBuilder not() {
        this.not(this);
        return this;
    }
    public EntityExpressionBuilder not(EntityExpressionBuilder expression) {
        this.expression.not(expression.getExpression());
        return this;
    }


    public EntityExpressionBuilder equalTo(Object object) {
        this.expression.equalTo(object);
        return this;
    }

    public EntityExpressionBuilder like(String regEx) {
        this.expression.like(regEx);
        return this;
    }

    public EntityExpression getExpression() {
        return expression;
    }

}
