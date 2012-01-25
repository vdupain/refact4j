package org.refact4j.eom.model;

import org.refact4j.eom.model.impl.EntityDescriptorRepositoryImpl;

public final class EntityDescriptorRepositoryBuilder {

    private final EntityDescriptorRepositoryImpl entityDescRepoImpl;

    private EntityDescriptorRepositoryBuilder() {
        entityDescRepoImpl = new EntityDescriptorRepositoryImpl();
    }

    public static EntityDescriptorRepositoryBuilder init() {
        return new EntityDescriptorRepositoryBuilder();
    }

    public static EntityDescriptorRepositoryBuilder init(EntityDescriptorRepository entityDescriptorRepository) {
        EntityDescriptorRepositoryBuilder entityDescriptorRepositoryBuilder = new EntityDescriptorRepositoryBuilder();
        if (entityDescriptorRepository != null) {
            for (EntityDescriptor entityDescriptor : entityDescriptorRepository) {
                entityDescriptorRepositoryBuilder.add(entityDescriptor);
            }
        }
        return entityDescriptorRepositoryBuilder;
    }

    public EntityDescriptorRepositoryBuilder add(EntityDescriptor entityDescriptor) {
        entityDescRepoImpl.add(entityDescriptor);
        return this;
    }

    public EntityDescriptorRepositoryBuilder add(EntityDescriptorRepository entityDescriptorRepository) {
        for (EntityDescriptor entityDescriptor : entityDescriptorRepository) {
            this.add(entityDescriptor);
        }
        return this;
    }

    public EntityDescriptorRepository getEntityDescriptorsRepository() {
        return this.entityDescRepoImpl;

    }

}
