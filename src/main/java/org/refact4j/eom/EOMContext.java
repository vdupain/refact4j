package org.refact4j.eom;

import org.refact4j.eom.annotations.AnnotedEntityBindableBeanConverter;
import org.refact4j.eom.annotations.AnnotedEntityBindableEntityConverter;
import org.refact4j.eom.model.EntityDescriptorRepositoryHolder;

public interface EOMContext extends EntityDescriptorRepositoryHolder {

    @SuppressWarnings("unchecked")
    AnnotedEntityBindableBeanConverter getBeanConverter();

    @SuppressWarnings("unchecked")
    AnnotedEntityBindableEntityConverter getEntityConverter();
}
