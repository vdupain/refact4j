package org.refact4j.expr;

import org.refact4j.eom.GetEntityFieldFunction;
import org.refact4j.eom.model.Field;
import org.refact4j.function.GetFieldFunction;

import java.util.function.Function;
import java.util.function.Supplier;

public class ExpressionBuilder implements Supplier<Expression> {
    private final Expression expression;

    private ExpressionBuilder() {
        expression = new Expression();
    }

    private ExpressionBuilder(Function function) {
        this(null, function);
    }

    private ExpressionBuilder(String property, Function function) {
        expression = new Expression(property, function);
    }

    public static ExpressionBuilder init() {
        return new ExpressionBuilder();
    }

    public static ExpressionBuilder init(Function function) {
        return new ExpressionBuilder(function);
    }

    public static ExpressionBuilder init(String property) {
        return new ExpressionBuilder(property, Function.identity());
    }

    public static ExpressionBuilder initBean(String property) {
        return new ExpressionBuilder(property, new GetFieldFunction(property));
    }

    public static ExpressionBuilder init(Field field) {
        return new ExpressionBuilder(field.getName(), new GetEntityFieldFunction(field));
    }

    public ExpressionBuilder greaterThan(Object value) {
        this.expression.greaterThan(value);
        return this;
    }

    public ExpressionBuilder greaterOrEqual(Object value) {
        this.expression.greaterOrEqual(value);
        return this;
    }

    public ExpressionBuilder lessOrEqual(Object value) {
        this.expression.lessOrEqual(value);
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
        this.expression.and(expression.get());
        return this;
    }

    public ExpressionBuilder or(ExpressionBuilder expression) {
        this.expression.or(expression.get());
        return this;
    }

    public ExpressionBuilder not() {
        this.expression.not(expression);
        return this;
    }

    public ExpressionBuilder not(ExpressionBuilder expression) {
        this.expression.not(expression.get());
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

    public Expression get() {
        return expression;
    }


}
