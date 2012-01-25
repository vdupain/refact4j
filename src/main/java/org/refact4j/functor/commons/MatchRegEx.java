package org.refact4j.functor.commons;

import org.refact4j.functor.AbstractBinaryPredicate;
import org.refact4j.visitor.Visitor;

import java.util.regex.Pattern;

public class MatchRegEx extends AbstractBinaryPredicate<String, String> {

    @Override
    protected Boolean evaluate(String arg, String pattern) {
        String regEx = (arg != null) ? arg : "";
        Pattern p = Pattern.compile(regEx);
        return p.matcher(regEx).find();
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof MatchRegExVisitor) {
            ((MatchRegExVisitor) visitor).visitMatchRegEx(this);
        }
    }

    public interface MatchRegExVisitor extends Visitor {
        void visitMatchRegEx(MatchRegEx matchRegEx);
    }

}
