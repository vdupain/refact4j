package org.refact4j.function.commons;

import org.refact4j.function.AbstractBiPredicate;
import org.refact4j.visitor.Visitor;

import java.util.regex.Pattern;

class MatchRegEx extends AbstractBiPredicate<String, String> {

    @Override
    public boolean test(String arg, String pattern) {
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
