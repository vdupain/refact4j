package org.refact4j.eom.metamodel;

import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryBuilder;

public final class EOMMetaModelRepository {
    private static final EntityDescriptorRepository REPO;

    private EOMMetaModelRepository() {
    }

    static {
        REPO = EntityDescriptorRepositoryBuilder.init().add(EntityDescriptorDesc.INSTANCE)
                .add(FieldDesc.INSTANCE).add(PropertyDesc.INSTANCE).add(DataTypeType.INSTANCE).add(
                EntityStringifierDesc.INSTANCE).add(EntityStringifierAppenderDesc.INSTANCE)
                .getEntityDescriptorsRepository();
    }

    public static EntityDescriptorRepository get() {
        return REPO;
    }

}
