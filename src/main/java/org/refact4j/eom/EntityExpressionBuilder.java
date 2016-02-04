package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.expr.ExpressionBuilder;

public final class EntityExpressionBuilder extends ExpressionBuilder {

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



}
