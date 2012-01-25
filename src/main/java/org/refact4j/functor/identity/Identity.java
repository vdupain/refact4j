package org.refact4j.functor.identity;

import org.refact4j.functor.AbstractUnaryFunctor;
import org.refact4j.visitor.Visitor;

/**
 * IdentityFunctor is a functor that returns the argument. This identity functor
 * is a kind of "x=f(x)" function.
 *
 * @param <T>
 */
public class Identity<T> extends AbstractUnaryFunctor<T, T> {

    /**
     * Returns the given argument.
     *
     * @return The argument.
     */
    @Override
    protected T evaluate(T arg) {
        return arg;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof IdentityVisitor) {
            ((IdentityVisitor) visitor).visitIdentity(this);
        }
    }

    public interface IdentityVisitor extends Visitor {
        void visitIdentity(Identity identityFunctor);
    }
}
