package org.refact4j.eom;

import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.util.Repository;
import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;
import org.refact4j.xml.ToXmlString;

public interface EntityStringifierRepo extends Repository<EntityDescriptor, EntityStringifier>, Visitable, ToXmlString {

    EntityStringifier put(EntityDescriptor entityDescriptor, EntityStringifier stringifier);

    EntityStringifier lookup(EntityDescriptor entityDescriptor);

    interface EntityStringifierRepoVisitor extends Visitor {
        void visitEntityStringifierRepository(EntityStringifierRepo entityObjectStringifierRepository);
    }

}
