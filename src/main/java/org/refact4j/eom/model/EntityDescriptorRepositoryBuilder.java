package org.refact4j.eom.model;

public final class EntityDescriptorRepositoryBuilder {

    private final EntityDescriptorRepository entityDescRepo;

    private EntityDescriptorRepositoryBuilder() {
        entityDescRepo = new EntityDescriptorRepository();
    }

    public static EntityDescriptorRepositoryBuilder init() {
        return new EntityDescriptorRepositoryBuilder();
    }

    public static EntityDescriptorRepositoryBuilder init(EntityDescriptorRepository entityDescriptorRepository) {
        EntityDescriptorRepositoryBuilder entityDescriptorRepositoryBuilder = new EntityDescriptorRepositoryBuilder();
        if (entityDescriptorRepository != null) {
            entityDescriptorRepository.values().stream().forEach(entityDescriptorRepositoryBuilder::add);
        }
        return entityDescriptorRepositoryBuilder;
    }

    public EntityDescriptorRepositoryBuilder add(EntityDescriptor entityDescriptor) {
        entityDescRepo.putIfAbsent(entityDescriptor.getName(), entityDescriptor);
        return this;
    }

    public EntityDescriptorRepositoryBuilder add(EntityDescriptorRepository entityDescriptorRepository) {
        entityDescriptorRepository.values().stream().forEach(this::add);
        return this;
    }

    public EntityDescriptorRepository get() {
        return this.entityDescRepo;

    }

}
