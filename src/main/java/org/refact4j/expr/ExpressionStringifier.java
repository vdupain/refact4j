package org.refact4j.expr;

import org.refact4j.functor.AbstractStringifier;
import org.refact4j.util.PrettyPrinter;

/**
 * Default Stringifier implementation for Expression.
 */
public class ExpressionStringifier extends AbstractStringifier<Expression<?>> {

    public static final ExpressionStringifier DEFAULT = new ExpressionStringifier() {

        @Override
        public String stringify(Expression<?> expression) {
            return new PrettyPrinter().toString(expression);
        }

    };

    @Override
    public String stringify(Expression<?> arg) {
        return DEFAULT.stringify(arg);
    }

}
