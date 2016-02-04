package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.expr.Expression;

/**
 * Expression for EntityObject. EntityExpression should be created by
 * ExpressionBuilder.
 */
public class EntityExpression extends Expression<EntityObject> {

    public EntityExpression() {
        super();
    }

    public EntityExpression(Field field) {
        super(field.getName(), new GetEntityFieldFunction(field));
    }

    public EntityExpression(String fieldName) {
        super(fieldName, new GetEntityFieldFunction(fieldName));
    }

}
