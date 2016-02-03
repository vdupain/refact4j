package org.refact4j.function.commons;

import org.refact4j.function.BinaryCompose;
import org.refact4j.function.Functions;
import org.refact4j.function.comparison.Equal;
import org.refact4j.function.logical.Or;
import org.refact4j.visitor.Visitor;

/**
 * In is an Unary Predicate that returns true when its argument is contains on
 * the given values.
 *
 * @param <T>
 */
public class In<T> implements org.refact4j.function.UnaryPredicate<T> {

    private final Object[] values;
    private java.util.function.Function<T, Boolean> pred;

    public In(Object[] values) {
        this.values = values;
        this.pred = Functions.bind2nd(new Equal(), values[0]);

        for (int i = 1; i < values.length; i++) {
            this.pred = new BinaryCompose(new Or(), pred, Functions.bind2nd(new Equal(), values[1]));
        }
    }

    @Override
    public boolean test(T arg) {
        return pred.apply(arg);
    }

    public Object[] getValues() {
        return values;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof InVisitor) {
            ((InVisitor) visitor).visitIn(this);
        }
    }

    public interface InVisitor extends Visitor {
        void visitIn(In<?> in);
    }

}
