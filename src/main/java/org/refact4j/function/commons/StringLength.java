package org.refact4j.function.commons;

import org.refact4j.function.AbstractFunction;
import org.refact4j.visitor.Visitor;

public class StringLength extends AbstractFunction<String, Integer> {

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
