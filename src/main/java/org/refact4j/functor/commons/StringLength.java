package org.refact4j.functor.commons;

import org.refact4j.functor.AbstractUnaryFunctor;
import org.refact4j.visitor.Visitor;

public class StringLength extends AbstractUnaryFunctor<String, Integer> {

    @Override
    protected Integer evaluate(String arg) {
        if (arg != null) {
            return arg.length();
        }
        return -1;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof StringLengthVisitor) {
            ((StringLengthVisitor) visitor).visitStringLength(this);
        }
    }

    public interface StringLengthVisitor extends Visitor {
        void visitStringLength(StringLength stringLength);
    }

}
