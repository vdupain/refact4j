package org.refact4j.functor.identity;

import org.refact4j.functor.AbstractBinaryFunctor;
import org.refact4j.visitor.Visitor;

/**
 * Project1st is a functor that returns the first argument. This Project1st functor
 * is a kind of "x=f(x, y)" function.
 * It is essentially a generalization of identity to the case of a Binary Function.
 *
 * @param <T1, T2>
 */
public class Project1st<T1, T2> extends AbstractBinaryFunctor<T1, T2, T1> {

    /**
     * Returns the first argument.
     *
     * @return The first argument.
     */
    @Override
    protected T1 evaluate(T1 firstArg, T2 secondArg) {
        return firstArg;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof Project1stVisitor) {
            ((Project1stVisitor) visitor).visitProject1st(this);
        }
    }

    public interface Project1stVisitor extends Visitor {
        void visitProject1st(Project1st project1st);
    }

}
