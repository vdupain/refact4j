package org.refact4j.eom.annotations;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.impl.AbstractEntityConverter;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.ManyToOneRelationField;

import java.lang.reflect.Method;

public class AnnotedEntityBindableEntityConverter<T> extends AbstractEntityConverter<T> {

    private final EntityAnnotationsHelper annotations = new EntityAnnotationsHelper();

    @Override
    public EntityObject convert(T t) {
        if (t == null) {
            return null;
        }
        EntityObject entityObject = null;
        Class<?> clazz = t.getClass().getInterfaces()[0];
        new AnnotationsValidator().validate(annotations, clazz);
        if (annotations.hasEntityBindableAnnotation(clazz)) {
            EntityBindable entityBindable = clazz.getAnnotation(EntityBindable.class);
            EntityDescriptor entityDescriptor = entityDescriptorRepository.getEntityDescriptor(entityBindable
                    .entityDescriptor());
            entityObject = EntityObjectBuilder.init(entityDescriptor).getEntity();

            for (Method method : clazz.getMethods()) {
                if (annotations.hasEntityFieldAnnotation(method)) {
                    EntityField fieldAnnotation = annotations.getEntityFieldAnnotation(method);
                    try {
                        final Object value = method.invoke(t);
                        org.refact4j.eom.model.Field entityObjectField = entityDescriptor.getField(fieldAnnotation
                                .name());
                        if (value != null && entityObjectField instanceof ManyToOneRelationField) {
                            entityObject.set(entityObjectField, entityDescriptorRepository.getContext()
                                    .getEntityConverter().convert(value).getKey());
                        } else {
                            entityObject.set(entityObjectField, value);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return entityObject;
    }

}
