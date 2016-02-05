package org.refact4j.eom.impl;

import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.EntityStringifierRepo;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryAware;
import org.refact4j.visitor.Visitor;

import java.util.HashMap;

public class EntityStringifierRepoImpl extends HashMap<EntityDescriptor, EntityStringifier> implements
        EntityStringifierRepo, EntityDescriptorRepositoryAware {

    private EntityDescriptorRepository entityDescriptorRepository;

    public void accept(Visitor visitor) {
        if (visitor instanceof EntityStringifierRepoVisitor) {
            ((EntityStringifierRepoVisitor) visitor).visitEntityStringifierRepository(this);
        }
    }

    public void setEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository) {
        this.entityDescriptorRepository = entityDescriptorRepository;
    }

    public String toXmlString() {
        DefaultEntityStringifierRepoVisitor visitor = new DefaultEntityStringifierRepoVisitor(
                entityDescriptorRepository);
        this.accept(visitor);
        return visitor.toXmlString();
    }

}
