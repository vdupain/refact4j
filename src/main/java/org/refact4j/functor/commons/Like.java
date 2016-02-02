package org.refact4j.functor.commons;

import org.refact4j.functor.AbstractBiPredicate;
import org.refact4j.visitor.Visitor;

public class Like extends AbstractBiPredicate<String, String> {

    private final MatchRegEx matchRegEx = new MatchRegEx();

    @Override
    public boolean test(String firstArg, String secondArg) {
        return matchRegEx.apply(firstArg, secondArg);
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
