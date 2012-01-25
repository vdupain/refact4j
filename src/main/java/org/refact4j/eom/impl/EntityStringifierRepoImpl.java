package org.refact4j.eom.impl;

import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.EntityStringifierRepo;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryAware;
import org.refact4j.util.RepositoryImpl;
import org.refact4j.visitor.Visitor;

public class EntityStringifierRepoImpl extends RepositoryImpl<EntityDescriptor, EntityStringifier> implements
        EntityStringifierRepo, EntityDescriptorRepositoryAware {

    private EntityDescriptorRepository entityDescriptorRepository;

    public EntityStringifier lookup(EntityDescriptor entityDescriptor) {
        try {
            return this.get(entityDescriptor);
        } catch (Exception e) {
            throw new RuntimeException("Missing Stringifier for EntityDescriptor '" + entityDescriptor
                    + "' in repository");
        }
    }

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
