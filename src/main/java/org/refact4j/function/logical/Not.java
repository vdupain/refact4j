package org.refact4j.function.logical;

import org.refact4j.visitor.Visitor;

/**
 * Not is a logical operator. This operator returns true when Boolean argument
 * arg is false.
 */
public class Not implements org.refact4j.function.UnaryPredicate<Boolean> {

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
