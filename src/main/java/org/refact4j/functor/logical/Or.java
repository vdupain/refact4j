package org.refact4j.functor.logical;

import org.refact4j.functor.AbstractBinaryPredicate;
import org.refact4j.visitor.Visitor;

/**
 * Or is a logical operator. This operator returns true when either of Boolean
 * arguments arg1 and arg2 are true.
 */
public class Or extends AbstractBinaryPredicate<Boolean, Boolean> {

    public Boolean evaluate(Boolean arg1, Boolean arg2) {
        return arg1 || arg2;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof OrVisitor) {
            ((OrVisitor) visitor).visitOr(this);
        }
    }

    public interface OrVisitor extends Visitor {
        void visitOr(Or or);
    }

}
