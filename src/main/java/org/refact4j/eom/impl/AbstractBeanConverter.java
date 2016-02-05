package org.refact4j.eom.impl;

import org.refact4j.core.Converter;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryAware;

public abstract class AbstractBeanConverter<T> implements Converter<EntityObject, T>, EntityDescriptorRepositoryAware {

    public void setEntityDescriptorRepository(EntityDescriptorRepository entityDescriptorRepository) {
    }

    public abstract T convert(EntityObject entityObject);

}
