package org.refact4j.eom;

import org.refact4j.eom.impl.EntityStringifierRepoImpl;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryAware;

public final class EntityStringifierRepoBuilder implements EntityDescriptorRepositoryAware {

    private final EntityStringifierRepoImpl stringifierRepositoryImpl;

    private EntityStringifierRepoBuilder() {
        stringifierRepositoryImpl = new EntityStringifierRepoImpl();
    }

    public static EntityStringifierRepoBuilder init() {
        return new EntityStringifierRepoBuilder();
    }

    public static EntityStringifierRepoBuilder init(EntityStringifierRepo stringifierRepository) {
        EntityStringifierRepoBuilder stringifierRepositoryBuilder = new EntityStringifierRepoBuilder();
        if (stringifierRepository != null) {
            for (EntityDescriptor entityDescriptor : stringifierRepository.keySet()) {
                stringifierRepositoryBuilder.add(entityDescriptor, stringifierRepository.get(entityDescriptor));
            }
        }
        return stringifierRepositoryBuilder;
    }

    public EntityStringifierRepoBuilder add(EntityDescriptor entityDescriptor, EntityStringifier stringifier) {
        stringifierRepositoryImpl.put(entityDescriptor, stringifier);
        return this;
    }

    public EntityStringifierRepo getStringifierRepository() {
        return this.stringifierRepositoryImpl;
    }

    public EntityStringifierRepoBuilder add(EntityStringifierRepo initalEntityStringifierRepository) {
        for (EntityDescriptor entityDescriptor : initalEntityStringifierRepository.keySet()) {
            this.add(entityDescriptor, initalEntityStringifierRepository.get(entityDescriptor));
        }
        return this;
    }

    public void setEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository) {
        this.stringifierRepositoryImpl.setEntityDescriptorRepository(entityDescriptorRepository);
    }

}
