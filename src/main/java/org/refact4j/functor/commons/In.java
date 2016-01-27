package org.refact4j.functor.commons;

import org.refact4j.functor.AbstractUnaryPredicate;
import org.refact4j.functor.BinaryCompose;
import org.refact4j.functor.Bind2nd;
import org.refact4j.functor.comparison.Equal;
import org.refact4j.functor.logical.Or;
import org.refact4j.visitor.Visitor;

/**
 * In is an Unary Predicate that returns true when its argument is contains on
 * the given values.
 *
 * @param <T>
 */
public class In<T> extends AbstractUnaryPredicate<T> {

    private final Object[] values;
    private java.util.function.Function<T,Boolean> pred;

    public In(Object[] values) {
        this.values = values;
        this.pred = new Bind2nd(new Equal(), values[0]);
        for (int i = 1; i < values.length; i++) {
            this.pred = new BinaryCompose(new Or(), pred, new Bind2nd(new Equal(), values[1]));
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
