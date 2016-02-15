package org.refact4j.eom.annotations;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.impl.AbstractBeanConverter;
import org.refact4j.eom.impl.EntitySet;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.ManyToOneRelationField;
import org.refact4j.util.MethodHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AnnotedEntityBindableBeanConverter<T> extends AbstractBeanConverter<T> {

    private final EntityAnnotationsHelper annotations = new EntityAnnotationsHelper();

    private EntitySet entitySet;

    @SuppressWarnings("unchecked")
    @Override
    public T convert(EntityObject entityObject) {
        if (entityObject == null) {
            return null;
        }
        T object = null;
        Class<?> beanClass = entityObject.getEntityDescriptor().getBeanClass();
        if (beanClass == null) {
            throw new RuntimeException("Bean class not set in object type : "
                    + entityObject.getEntityDescriptor().getName());
        }
        new AnnotationsValidator().validate(annotations, beanClass);
        if (annotations.hasEntityBindableAnnotation(beanClass)) {
            object = (T) Proxy.newProxyInstance(beanClass.getClassLoader(), new Class[]{beanClass},
                    new EntityInvocationHandler(entityObject));
        }
        return object;
    }

    public void setEntitySet(EntitySet entitySet) {
        this.entitySet = entitySet;
    }

    public EntityAnnotationsHelper getAnnotations() {
        return annotations;
    }

    class EntityInvocationHandler implements InvocationHandler {

        private final EntityObject entityObject;

        public EntityInvocationHandler(EntityObject entityObject) {
            this.entityObject = entityObject;
        }

        public Object invoke(Object obj, Method method, Object[] aobj) throws Throwable {
            if (MethodHelper.isToStringMethod(method)) {
                return this.entityObject.toString();
            }
            if (method.getName().equals("toXmlString") && method.getGenericReturnType().equals(String.class)) {
                return this.entityObject.toXmlString();
            }
            Object result = null;
            if (annotations.hasEntityFieldAnnotation(method)) {
                EntityField entityObjectFieldAnnotation = annotations.getEntityFieldAnnotation(method);

                Field field = this.entityObject.getEntityDescriptor().getField(entityObjectFieldAnnotation.name());
                if (field instanceof ManyToOneRelationField) {
                    Key key = (Key) this.entityObject.get(field);
                    if (key != null) {
                        EntityObject targetEntity = EntityObjectBuilder.init(key.getEntityDescriptor()).get();
                        for (Field f : targetEntity.getEntityDescriptor().getFields()) {
                            targetEntity.set(f, key.getFieldValue(f));
                        }
                        AnnotedEntityBindableBeanConverter<?> converter = new AnnotedEntityBindableBeanConverter<>();
                        if (entitySet != null) {
                            final Key targetKey = targetEntity.getKey();
                            EntityObject target = entitySet.stream()
                                    .filter(p -> p.getKey().equals(targetKey))
                                    .findFirst().get();

                            if (target != null) {
                                targetEntity = target;
                            }
                            converter.setEntitySet(entitySet);
                        }
                        result = converter.convert(targetEntity);
                    }
                } else {
                    result = this.entityObject.get(field);
                    if (method.getReturnType().equals(Boolean.TYPE) && result == null) {
                        result = Boolean.FALSE;
                    }
                }
            }
            return result;
        }

    }
}
