package org.refact4j.eom.impl;

import org.refact4j.eom.EOMContext;
import org.refact4j.eom.annotations.AnnotedEntityBindableBeanConverter;
import org.refact4j.eom.annotations.AnnotedEntityBindableEntityConverter;
import org.refact4j.eom.model.EntityDescriptorRepository;

public final class EOMContextImpl implements EOMContext {
    private static EOMContextImpl instance;

    private final EntityDescriptorRepository entityDescriptorRepository;

    @SuppressWarnings("unchecked")
    private final AnnotedEntityBindableBeanConverter beanConverter = new AnnotedEntityBindableBeanConverter();
    @SuppressWarnings("unchecked")
    private final AnnotedEntityBindableEntityConverter entityConverter = new AnnotedEntityBindableEntityConverter();

    private EOMContextImpl(EntityDescriptorRepository entityDescriptorRepository) {
        this.entityDescriptorRepository = entityDescriptorRepository;
        beanConverter.setEntityDescriptorRepository(entityDescriptorRepository);
        entityConverter.setEntityDescriptorRepository(entityDescriptorRepository);
    }

    public static EOMContextImpl newInstance(EntityDescriptorRepository entityDescriptorRepository) {
        instance = new EOMContextImpl(entityDescriptorRepository);
        return getInstance();
    }

    private static EOMContextImpl getInstance() {
        return instance;
    }

    public EntityDescriptorRepository get() {
        return this.entityDescriptorRepository;
    }

    @SuppressWarnings("unchecked")
    public AnnotedEntityBindableBeanConverter getBeanConverter() {
        return beanConverter;
    }

    @SuppressWarnings("unchecked")
    public AnnotedEntityBindableEntityConverter getEntityConverter() {
        return entityConverter;
    }
}
