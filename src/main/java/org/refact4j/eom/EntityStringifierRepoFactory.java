package org.refact4j.eom;

import org.refact4j.eom.impl.AbstractEntityStringifierRepoFactory;
import org.refact4j.eom.metamodel.EOMMetaModelRepository;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryBuilder;

/**
 * EntityStringifierRepoFactory is a factory to create EntityStringifier
 * repository from FIELD_XML description.
 */
public final class EntityStringifierRepoFactory extends AbstractEntityStringifierRepoFactory {

    private EntityStringifierRepoFactory(EntityDescriptorRepository entityDescriptorRepository, String xmlStringifier,
                                         EntityDescriptorRepository metaModelRepository) {
        super(entityDescriptorRepository, xmlStringifier, metaModelRepository);
    }

    public static EntityStringifierRepositoryFactory init(EntityDescriptorRepository entityDescriptorRepository,
                                                          String xmlStringifier) {
        return new EntityStringifierRepoFactory(entityDescriptorRepository, xmlStringifier, EOMMetaModelRepository
                .get());
    }

    public static EntityStringifierRepositoryFactory init(EntityDescriptorRepository entityDescriptorRepository,
                                                          String xmlStringifier, EntityDescriptorRepository metaModelEntityDescriptorRepository) {
        return new EntityStringifierRepoFactory(entityDescriptorRepository, xmlStringifier,
                metaModelEntityDescriptorRepository);
    }

    public static EntityStringifierRepositoryFactory init(EntityDescriptor entityDescriptor, String xmlStringifier) {
        return new EntityStringifierRepoFactory(EntityDescriptorRepositoryBuilder.init().add(entityDescriptor)
                .getEntityDescriptorsRepository(), xmlStringifier, EOMMetaModelRepository.get());
    }

}
