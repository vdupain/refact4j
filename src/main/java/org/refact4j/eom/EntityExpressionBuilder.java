package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.function.ConstantFunction;

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

    public EntityExpressionBuilder isNotNull() {
        this.expression.isNotNull();
        return this;
    }

    public EntityExpressionBuilder in(Object[] values) {
        this.expression.in(values);
        return this;
    }

    public EntityExpressionBuilder notIn(Object[] values) {
        this.expression.notIn(values);
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

    public EntityExpressionBuilder not(EntityExpressionBuilder expression) {
        this.expression.not(expression.getExpression());
        return this;
    }

    public EntityExpressionBuilder equalTo(ConstantFunction constantFunctor) {
        this.expression.equalTo(constantFunctor);
        return this;
    }

    public EntityExpressionBuilder equalTo(EntityObject entityObject) {
        this.expression.equalTo(new ConstantFunction<>(entityObject.getKey()));
        return this;
    }

    public EntityExpressionBuilder equalTo(Object object) {
        this.expression.equalTo(new ConstantFunction<>(object));
        return this;
    }

    public EntityExpressionBuilder notEqualTo(Object object) {
        this.expression.not(equalTo(new ConstantFunction<>(object)).getExpression());
        return this;
    }

    public EntityExpressionBuilder maxLength(int maxLength) {
        this.expression.maxLength(maxLength);
        return this;
    }

    public EntityExpressionBuilder minLength(int minLength) {
        this.expression.minLength(minLength);
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
