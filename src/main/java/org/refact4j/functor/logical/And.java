package org.refact4j.functor.logical;

import org.refact4j.functor.AbstractBinaryPredicate;
import org.refact4j.visitor.Visitor;

/**
 * And is a logical operator. This operator returns true when Boolean arguments
 * arg1 and arg2 are both true.
 */

public class And extends AbstractBinaryPredicate<Boolean, Boolean> {

    public boolean evaluate(Boolean arg1, Boolean arg2) {
        return arg1 && arg2;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof AndVisitor) {
            ((AndVisitor) visitor).visitAnd(this);
        }
    }

    public interface AndVisitor extends Visitor {
        void visitAnd(And and);
    }

}
