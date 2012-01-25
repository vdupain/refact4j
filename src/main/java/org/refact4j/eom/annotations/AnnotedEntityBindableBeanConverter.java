package org.refact4j.eom.annotations;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.refact4j.eom.EntityFinder;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.impl.AbstractBeanConverter;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.ManyToOneRelationField;
import org.refact4j.util.MethodHelper;

public class AnnotedEntityBindableBeanConverter<T> extends AbstractBeanConverter<T> {

	private final EntityAnnotationsHelper annotations = new EntityAnnotationsHelper();

	private EntityFinder finder;

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
			object = (T) Proxy.newProxyInstance(beanClass.getClassLoader(), new Class[] { beanClass },
					new EntityInvocationHandler(entityObject));
		}
		return object;
	}

	public void setEntityFinder(EntityFinder finder) {
		this.finder = finder;
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
						EntityObject targetEntity = EntityObjectBuilder.init(key.getEntityDescriptor()).getEntity();
						for (Field f : targetEntity.getEntityDescriptor().getFields()) {
							targetEntity.set(f, key.getFieldValue(f));
						}
						AnnotedEntityBindableBeanConverter<?> functor = new AnnotedEntityBindableBeanConverter<Object>();
						if (finder != null) {
							EntityObject target = finder.findByIdentifier(targetEntity.getKey());
							if (target != null) {
								targetEntity = target;
							}
							functor.setEntityFinder(finder);
						}
						result = functor.convert(targetEntity);
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

	public EntityAnnotationsHelper getAnnotations() {
		return annotations;
	}
}
