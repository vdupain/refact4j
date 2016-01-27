package org.refact4j.functor.logical;

import org.refact4j.functor.AbstractUnaryPredicate;
import org.refact4j.visitor.Visitor;

/**
 * Not is a logical operator. This operator returns true when Boolean argument
 * arg is false.
 */
public class Not extends AbstractUnaryPredicate<Boolean> {

    public boolean test(Boolean arg) {
        return !arg;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof NotVisitor) {
            ((NotVisitor) visitor).visitNot(this);
        }
    }

    public interface NotVisitor extends Visitor {
        void visitNot(Not not);
    }

}
