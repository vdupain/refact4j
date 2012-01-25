package org.refact4j.model;

import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryBuilder;

public class DummyRepository {

    private static final EntityDescriptorRepository repository;

    static {
        EntityDescriptorRepository dummyRepository = EntityDescriptorRepositoryBuilder.init().add(FooDesc.INSTANCE)
                .getEntityDescriptorsRepository();

        repository = EntityDescriptorRepositoryBuilder.init(dummyRepository)
                .add(BarDesc.INSTANCE)
                .add(CompoundKeyFooDesc.INSTANCE)
                .add(CompoundKeyFoo2Desc.INSTANCE)
                .add(EmployeeDesc.INSTANCE)
                .add(EmployeeInfoDesc.INSTANCE)
                .getEntityDescriptorsRepository();
    }

    public static EntityDescriptorRepository get() {
        return repository;
    }

}
