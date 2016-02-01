package org.refact4j.eom.impl;

import org.refact4j.eom.EntityFunction;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryAware;

public abstract class AbstractBeanConverter<T> implements EntityFunction<T>, EntityDescriptorRepositoryAware {
    private EntityDescriptorRepository entityDescriptorRepository;

    public void setEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository) {
        this.entityDescriptorRepository = entityDescriptorRepository;
    }

    public abstract T convert(EntityObject entityObject);

    public T apply(EntityObject entityObject) {
        return this.convert(entityObject);
    }

}
