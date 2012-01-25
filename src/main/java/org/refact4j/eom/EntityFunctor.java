package org.refact4j.eom;

import org.refact4j.functor.UnaryFunctor;
import org.refact4j.visitor.Visitor;

/**
 * EntityFunctor interface is an Unary Functor for EntityObject.
 *
 * @param <T>
 */
public interface EntityFunctor<T> extends UnaryFunctor<EntityObject, T> {

    public interface EntityFunctorVisitor extends Visitor {
        void visitEntityFunctor(EntityFunctor<?> entityFunctor);
    }

}
