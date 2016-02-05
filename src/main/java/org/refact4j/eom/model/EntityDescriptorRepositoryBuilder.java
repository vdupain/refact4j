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
            entityDescriptorRepository.values().stream().forEach(e -> entityDescriptorRepositoryBuilder.add(e));
        }
        return entityDescriptorRepositoryBuilder;
    }

    public EntityDescriptorRepositoryBuilder add(EntityDescriptor entityDescriptor) {
        entityDescRepoImpl.putIfAbsent(entityDescriptor.getName(), entityDescriptor);
        return this;
    }

    public EntityDescriptorRepositoryBuilder add(EntityDescriptorRepository entityDescriptorRepository) {
        entityDescriptorRepository.values().stream().forEach(e -> add(e));
        return this;
    }

    public EntityDescriptorRepository getEntityDescriptorsRepository() {
        return this.entityDescRepoImpl;

    }

}
