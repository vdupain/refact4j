package org.refact4j.eom.model;

import org.refact4j.eom.EOMContext;
import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;
import org.refact4j.xml.ToXmlString;

import java.util.Map;

public interface EntityDescriptorRepository extends Map<String, EntityDescriptor>, Visitable, ToXmlString {

    EntityDescriptor getEntityDescriptor(String name);

    EOMContext getContext();

    interface EntityDescriptorRepositoryVisitor extends Visitor {
        void visitEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository);
    }

}
