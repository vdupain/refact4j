package org.refact4j.function.commons;

import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;

import java.util.function.Function;

public class StringLength implements Function<String, Integer>, Visitable {


    @Override
    public Integer apply(String value) {
        return value != null ? value.length() : -1;
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
