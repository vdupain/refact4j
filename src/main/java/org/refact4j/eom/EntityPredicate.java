package org.refact4j.eom;

import org.refact4j.functor.UnaryPredicate;
import org.refact4j.visitor.Visitor;

/**
 * EntityPredicate interface is an Unary Predicate for EntityObject.
 */
public interface EntityPredicate extends UnaryPredicate<EntityObject> {

    public interface EntityPredicateVisitor extends Visitor {
        void visitEntityPredicate(EntityPredicate entityObjectPredicate);
    }

}
