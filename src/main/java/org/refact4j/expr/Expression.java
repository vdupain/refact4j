package org.refact4j.expr;

import org.refact4j.functor.*;
import org.refact4j.functor.commons.Between;
import org.refact4j.functor.commons.In;
import org.refact4j.functor.commons.InstanceOf;
import org.refact4j.functor.commons.Like;
import org.refact4j.functor.commons.NotIn;
import org.refact4j.functor.commons.StringLength;
import org.refact4j.functor.comparison.Equal;
import org.refact4j.functor.comparison.Greater;
import org.refact4j.functor.comparison.GreaterEqual;
import org.refact4j.functor.comparison.Less;
import org.refact4j.functor.comparison.LessEqual;
import org.refact4j.functor.comparison.NotEqual;
import org.refact4j.functor.comparison.NotNull;
import org.refact4j.functor.comparison.Null;
import org.refact4j.functor.identity.Identity;
import org.refact4j.functor.logical.And;
import org.refact4j.functor.logical.Not;
import org.refact4j.functor.logical.Or;
import org.refact4j.visitor.Visitor;

/**
 * Expression defines an object-level representation of a database query where
 * clause. An expression is a tree-like structure that defines the selection
 * criteria for a query against objects.
 */
public class Expression<T> extends AbstractUnaryPredicate<T> {

    private String name;

    private java.util.function.Function<T,Boolean> predicate;

    private java.util.function.Function<T,?> function = new Identity<T>();

    public Expression() {
    }

    public Expression(String name) {
        this.name = name;
    }

    protected Expression(String name, java.util.function.Function<T,?> function) {
        this.name = name;
        this.function = function;
    }

    public Expression(java.util.function.Function<T,Boolean> function) {
        this.function = function;
    }

    public Expression<T> greaterThan(Object value) {
        predicate = new CompositeUnaryPredicate<T>(new Greater<Object>(), function, value);
        return this;
    }

    public Expression<T> greaterOrEqual(Object value) {
        predicate = new CompositeUnaryPredicate<T>(new GreaterEqual<Object>(), function, value);
        return this;
    }

    public Expression<T> lessThan(Object value) {
        predicate = new CompositeUnaryPredicate<T>(new Less<Object>(), function, value);
        return this;
    }

    public Expression<T> lessOrEqual(Object value) {
        predicate = new CompositeUnaryPredicate<T>(new LessEqual<Object>(), function, value);
        return this;
    }

    public Expression<T> between(Object infValue, Object supValue) {
        predicate = new UnaryCompose(new Between<Object>(infValue, supValue), function);
        return this;
    }

    public Expression<T> isNull() {
        predicate = new UnaryCompose(new Null<Object>(), function);
        return this;
    }

    public Expression<T> isNotNull() {
        predicate = new UnaryCompose(new NotNull<Object>(), function);
        return this;
    }

    public Expression<T> in(Object[] objects) {
        predicate = new UnaryCompose(new In<Object>(objects), function);
        return this;
    }

    public Expression<T> notIn(Object[] objects) {
        predicate = new UnaryCompose(new NotIn<Object>(objects), function);
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
        return this.equalTo(new ConstantFunction<Object>(value));
    }

    public Expression<T> notEqualTo(Object value) {
        return this.notEqualTo(new ConstantFunction<Object>(value));
    }

    public Expression<T> equalTo(ConstantFunction<Object> constantFunctor) {
        predicate = new CompositeUnaryPredicate<T>(new Equal<Object>(), function, constantFunctor);
        return this;
    }

    public Expression<T> notEqualTo(ConstantFunction<Object> constantFunctor) {
        predicate = new CompositeUnaryPredicate<T>(new NotEqual<Object>(), function, constantFunctor);
        return this;
    }

    public Expression<T> like(String regEx) {
        predicate = new CompositeUnaryPredicate<T>(new Like(), function, regEx);
        return this;
    }

	public Expression<T> instanceOf(Class<?> clazz) {
        predicate = new CompositeUnaryPredicate<T>(new InstanceOf(), function, clazz);
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

    public java.util.function.Function<T,?> getFunctor() {
        return predicate;
    }

    public String getPropertyName() {
        return name;
    }

    public boolean evaluate(T arg) {
        return this.apply(arg);
    }

    public Boolean apply(T arg) {
        return this.predicate.apply(arg);
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof ExpressionVisitor) {
            ((ExpressionVisitor) visitor).visitExpression(this);
        }
    }

    public interface ExpressionVisitor extends Visitor {
        void visitExpression(Expression<?> expression);
    }

    @Override
    public String toString() {
        return ExpressionStringifier.DEFAULT.stringify(this);
    }

}
