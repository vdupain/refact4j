package org.refact4j.eom.model;

import org.refact4j.eom.EOMContext;
import org.refact4j.util.Repository;
import org.refact4j.visitor.Visitable;
import org.refact4j.visitor.Visitor;
import org.refact4j.xml.ToXmlString;

public interface EntityDescriptorRepository extends Repository<String, EntityDescriptor>, Visitable, ToXmlString {

    EntityDescriptor getEntityDescriptor(String name);

    EOMContext getContext();

    interface EntityDescriptorRepositoryVisitor extends Visitor {
        void visitEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository);
    }

}
