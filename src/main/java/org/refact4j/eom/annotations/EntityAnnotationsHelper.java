package org.refact4j.eom.annotations;

import java.lang.reflect.Method;

public class EntityAnnotationsHelper {

    @SuppressWarnings("unchecked")
    public EntityBindable getEntityBindableAnnotation(Class clazz) {
        return (EntityBindable) clazz.getAnnotation(EntityBindable.class);
    }

    @SuppressWarnings("unchecked")
    public boolean hasEntityBindableAnnotation(Class clazz) {
        return clazz.isAnnotationPresent(EntityBindable.class);
    }

    public EntityField getEntityFieldAnnotation(Method method) {
        return method.getAnnotation(EntityField.class);
    }

    public boolean hasEntityFieldAnnotation(Method method) {
        return method.isAnnotationPresent(EntityField.class);
    }

}
