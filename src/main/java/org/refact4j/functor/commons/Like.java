package org.refact4j.functor.commons;

import org.refact4j.functor.AbstractBinaryPredicate;
import org.refact4j.visitor.Visitor;

public class Like extends AbstractBinaryPredicate<String, String> {

    private final MatchRegEx matchRegEx = new MatchRegEx();

    @Override
    protected Boolean evaluate(String firstArg, String secondArg) {
        return matchRegEx.eval(firstArg, secondArg);
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof LikeVisitor) {
            ((LikeVisitor) visitor).visitLike(this);
        }
    }

    public interface LikeVisitor extends Visitor {
        void visitLike(Like like);
    }

}
