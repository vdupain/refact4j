package org.refact4j.eom;

import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;
import org.refact4j.xml.ToXmlString;

import java.util.Map;

public interface EntityStringifierRepo extends Map<EntityDescriptor, EntityStringifier>, Visitable, ToXmlString {

    EntityStringifier put(EntityDescriptor entityDescriptor, EntityStringifier stringifier);

    interface EntityStringifierRepoVisitor extends Visitor {
        void visitEntityStringifierRepository(EntityStringifierRepo entityObjectStringifierRepository);
    }

}
