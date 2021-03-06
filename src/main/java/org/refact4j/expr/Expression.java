package org.refact4j.expr;

import org.refact4j.function.BinaryCompose;
import org.refact4j.function.ComposeFunction;
import org.refact4j.function.CompositeUnaryPredicate;
import org.refact4j.function.commons.*;
import org.refact4j.function.comparison.*;
import org.refact4j.function.logical.And;
import org.refact4j.function.logical.Not;
import org.refact4j.function.logical.Or;
import org.refact4j.util.PrettyPrinter;
import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Expression defines an object-level representation of a database query where
 * clause. An expression is a tree-like structure that defines the selection
 * criteria for a query against objects.
 */
public class Expression<T> implements Predicate<T>, Visitable {

    private final String name;

    private java.util.function.Function<T, Boolean> predicate;

    private java.util.function.Function<T, ?> function = Function.identity();

    public Expression() {
        this(null, Function.identity());
    }

    public Expression(String name) {
        this(name, Function.identity());
    }

    Expression(String name, Function<T, ?> function) {
        this.name = name;
        this.function = function;
    }

    public Expression<T> greaterThan(Object value) {
        predicate = new CompositeUnaryPredicate<>(new Greater<>(), function, value);
        return this;
    }

    public Expression<T> greaterOrEqual(Object value) {
        predicate = new CompositeUnaryPredicate<>(new GreaterEqual<>(), function, value);
        return this;
    }

    public Expression<T> lessThan(Object value) {
        predicate = new CompositeUnaryPredicate<>(new Less<>(), function, value);
        return this;
    }

    public Expression<T> lessOrEqual(Object value) {
        predicate = new CompositeUnaryPredicate<>(new LessEqual<>(), function, value);
        return this;
    }

    public Expression<T> between(Object infValue, Object supValue) {
        predicate = new ComposeFunction(new Between<>(infValue, supValue), function);
        return this;
    }

    public Expression<T> isNull() {
        predicate = new ComposeFunction(new Null<>(), function);
        return this;
    }

    public Expression<T> in(Object[] objects) {
        predicate = new ComposeFunction(new In<>(objects), function);
        return this;
    }

    public Expression<T> and(Expression<T> expression) {
        predicate = new BinaryCompose<>(new And(), this.predicate, expression.predicate);
        return this;
    }

    public Expression<T> or(Expression<T> expression) {
        predicate = new BinaryCompose<>(new Or(), this.predicate, expression.predicate);
        return this;
    }

    public Expression<T> not() {
        predicate = new ComposeFunction<>(new Not(), predicate);
        return this;
    }

    public Expression<T> not(Expression<T> expression) {
        predicate = new ComposeFunction<>(new Not(), expression.predicate);
        return this;
    }

    public Expression<T> equalTo(Object value) {
        predicate = new CompositeUnaryPredicate<>(new Equal<>(), function, value);
        return this;
    }

    public Expression<T> like(String regEx) {
        predicate = new CompositeUnaryPredicate<>(new Like(), function, regEx);
        return this;
    }

    public Expression<T> instanceOf(Class<?> clazz) {
        predicate = new CompositeUnaryPredicate<>(new InstanceOf(), function, clazz);
        return this;
    }

    public Expression<T> maxLength(int maxLength) {
        predicate = new CompositeUnaryPredicate(new LessEqual<Integer>(), new StringLength(), maxLength);
        return this;
    }

    public Expression<T> minLength(int minLength) {
        predicate = new CompositeUnaryPredicate(new GreaterEqual<Integer>(), new StringLength(), minLength);
        return this;
    }

    public Function<T, Boolean> getPredicate() {
        return predicate;
    }

    public String getPropertyName() {
        return name;
    }

    public boolean test(T arg) {
        return this.predicate.apply(arg);
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof ExpressionVisitor) {
            ((ExpressionVisitor) visitor).visitExpression(this);
        }
    }

    @Override
    public String toString() {
        return new PrettyPrinter().toString(this);
    }

    public interface ExpressionVisitor extends Visitor {
        void visitExpression(Expression expression);
    }

}
