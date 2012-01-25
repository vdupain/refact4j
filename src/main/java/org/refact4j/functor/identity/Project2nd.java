package org.refact4j.functor.identity;

import org.refact4j.functor.AbstractBinaryFunctor;
import org.refact4j.visitor.Visitor;

/**
 * Project2nd is a functor that returns the second argument. This Project2nd
 * functor is a kind of "y=f(x, y)" function. It is essentially a generalization
 * of identity to the case of a Binary Function.
 *
 * @param <T1, T2>
 */
public class Project2nd<T1, T2> extends AbstractBinaryFunctor<T1, T2, T2> {

    /**
     * Returns the second argument.
     *
     * @return The second argument.
     */
    @Override
    protected T2 evaluate(T1 firstArg, T2 secondArg) {
        return secondArg;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof Project2ndVisitor) {
            ((Project2ndVisitor) visitor).visitProject2nd(this);
        }
    }

    public interface Project2ndVisitor extends Visitor {
        void visitProject2nd(Project2nd project2nd);
    }

}
