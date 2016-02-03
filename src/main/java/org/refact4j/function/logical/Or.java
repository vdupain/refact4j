package org.refact4j.function.logical;

import org.refact4j.function.AbstractBiPredicate;
import org.refact4j.visitor.Visitor;

/**
 * Or is a logical operator. This operator returns true when either of Boolean
 * arguments arg1 and arg2 are true.
 */
public class Or extends AbstractBiPredicate<Boolean, Boolean> {

    public boolean test(Boolean arg1, Boolean arg2) {
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
