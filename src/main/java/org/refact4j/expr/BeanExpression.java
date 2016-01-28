package org.refact4j.expr;

import org.refact4j.functor.GetFieldFunctor;

/**
 * Expression for Java Beans. BeanExpression should be created by
 * ExpressionBuilder.
 */
class BeanExpression extends Expression<Object> {

    public BeanExpression() {
        super();
    }

    public BeanExpression(String fieldName) {
        super(fieldName, new GetFieldFunctor(fieldName));
    }

}
