package org.refact4j.expr;

import org.refact4j.function.GetFieldFunction;

public final class BeanExpressionBuilder extends ExpressionBuilder {

    private BeanExpressionBuilder() {
        expression = new Expression();
    }

    private BeanExpressionBuilder(String property) {
        expression = new Expression(property, new GetFieldFunction(property));
    }

    public static BeanExpressionBuilder init() {
        return new BeanExpressionBuilder();
    }

    public static BeanExpressionBuilder init(String property) {
        return new BeanExpressionBuilder(property);
    }




}
