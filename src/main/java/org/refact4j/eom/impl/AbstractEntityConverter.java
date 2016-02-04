package org.refact4j.eom.impl;

import org.refact4j.core.Converter;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryAware;

public abstract class AbstractEntityConverter<T> implements Converter<T, EntityObject>, EntityDescriptorRepositoryAware {

    protected EntityDescriptorRepository entityDescriptorRepository;

    public void setEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository) {
        this.entityDescriptorRepository = entityDescriptorRepository;
    }

}
