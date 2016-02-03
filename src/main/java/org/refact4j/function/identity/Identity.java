package org.refact4j.function.identity;

import org.refact4j.function.AbstractFunction;
import org.refact4j.visitor.Visitor;

/**
 * IdentityFunctor is a function that returns the argument. This identity function
 * is a kind of "x=f(x)" function.
 *
 * @param <T>
 */
public class Identity<T> extends AbstractFunction<T, T> {

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
