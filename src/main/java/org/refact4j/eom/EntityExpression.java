package org.refact4j.eom;

import org.refact4j.eom.model.Field;
import org.refact4j.expr.Expression;

/**
 * Expression for EntityObject. EntityExpression should be created by
 * ExpressionBuilder.
 */
public class EntityExpression extends Expression<EntityObject> implements EntityPredicate {

    public EntityExpression() {
        super();
    }

    public EntityExpression(Field field) {
        super(field.getName(), new GetEntityFieldFunctor(field));
    }

    public EntityExpression(String fieldName) {
        super(fieldName, new GetEntityFieldFunctor(fieldName));
    }

}
