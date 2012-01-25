package org.refact4j.eom.annotations;

import java.lang.reflect.Method;

class AnnotationsValidator {
    private EntityAnnotationsHelper annotationsHelper;

    public void validate(EntityAnnotationsHelper annotationsHelper, Class<?> clazz) {
        this.annotationsHelper = annotationsHelper;
        validate(clazz);
    }

    void validate(Class<?> clazz) {
        for (Method method : clazz.getMethods()) {
            if (annotationsHelper.hasEntityFieldAnnotation(method)) {
                validateMethod(method);
            }
        }
    }

    private void validateMethod(final Method method) {
        // final EntityField entityObjectFieldAnnotation =
        // annotationsHelper.getEntityFieldAnnotation(method);
        // final Class<?> returnType = method.getReturnType();
    }

}
