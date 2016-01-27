package org.refact4j.eom;

import org.refact4j.visitor.Visitor;

/**
 * EntityFunctor interface is an Unary Functor for EntityObject.
 *
 * @param <T>
 */
public interface EntityFunctor<T> extends java.util.function.Function<EntityObject, T> {

    interface EntityFunctorVisitor extends Visitor {
        void visitEntityFunctor(EntityFunctor<?> entityFunctor);
    }

}
