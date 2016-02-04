package org.refact4j.eom;

import org.refact4j.eom.annotations.AnnotedEntityBindableBeanConverter;
import org.refact4j.eom.annotations.AnnotedEntityBindableEntityConverter;
import org.refact4j.eom.model.EntityDescriptorRepository;

import java.util.function.Supplier;

public interface EOMContext extends Supplier<EntityDescriptorRepository> {

    @SuppressWarnings("unchecked")
    AnnotedEntityBindableBeanConverter getBeanConverter();

    @SuppressWarnings("unchecked")
    AnnotedEntityBindableEntityConverter getEntityConverter();
}
