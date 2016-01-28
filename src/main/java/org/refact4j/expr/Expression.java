package org.refact4j.expr;

import org.refact4j.functor.*;
import org.refact4j.functor.commons.*;
import org.refact4j.functor.comparison.*;
import org.refact4j.functor.identity.Identity;
import org.refact4j.functor.logical.And;
import org.refact4j.functor.logical.Not;
import org.refact4j.functor.logical.Or;
import org.refact4j.util.PrettyPrinter;
import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

import java.util.function.Predicate;

/**
 * Expression defines an object-level representation of a database query where
 * clause. An expression is a tree-like structure that defines the selection
 * criteria for a query against objects.
 */
public class Expression<T> implements Predicate<T>, UnaryPredicate<T>, Visitable {

    private String name;

    private java.util.function.Function<T, Boolean> predicate;

    private java.util.function.Function<T, ?> function = new Identity<T>();

    public Expression() {
    }

    public Expression(String name) {
        this.name = name;
    }

    protected Expression(String name, java.util.function.Function<T, ?> function) {
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
        predicate = new UnaryCompose(new Between<>(infValue, supValue), function);
        return this;
    }

    public Expression<T> isNull() {
        predicate = new UnaryCompose(new Null<>(), function);
        return this;
    }

    public Expression<T> isNotNull() {
        predicate = new UnaryCompose(new NotNull<>(), function);
        return this;
    }

    public Expression<T> in(Object[] objects) {
        predicate = new UnaryCompose(new In<>(objects), function);
        return this;
    }

    public Expression<T> notIn(Object[] objects) {
        predicate = new UnaryCompose(new NotIn<>(objects), function);
        return this;
    }

    public Expression<T> and(Expression<T> expression) {
        predicate = new BinaryCompose<Boolean, Boolean, T, Boolean>(new And(), this.predicate, expression.predicate);
        return this;
    }

    public Expression<T> or(Expression<T> expression) {
        predicate = new BinaryCompose<Boolean, Boolean, T, Boolean>(new Or(), this.predicate, expression.predicate);
        return this;
    }

    public Expression<T> not(Expression<T> expression) {
        predicate = new UnaryCompose<Boolean, T, Boolean>(new Not(), expression.predicate);
        return this;
    }

    public Expression<T> equalTo(Object value) {
        return this.equalTo(new ConstantFunction<>(value));
    }

    public Expression<T> notEqualTo(Object value) {
        return this.notEqualTo(new ConstantFunction<>(value));
    }

    public Expression<T> equalTo(ConstantFunction<Object> constantFunctor) {
        predicate = new CompositeUnaryPredicate<>(new Equal<>(), function, constantFunctor);
        return this;
    }

    public Expression<T> notEqualTo(ConstantFunction<Object> constantFunctor) {
        predicate = new CompositeUnaryPredicate<>(new NotEqual<>(), function, constantFunctor);
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

    public java.util.function.Function<T, ?> getFunctor() {
        return predicate;
    }

    public String getPropertyName() {
        return name;
    }

    public boolean test(T arg) {
        return this.predicate.apply(arg);
    }

    public Boolean apply(T arg) {
        return test(arg);
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
        void visitExpression(Expression<?> expression);
    }

}
