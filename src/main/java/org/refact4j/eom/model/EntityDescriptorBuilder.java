package org.refact4j.eom.model;

import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.EntityStringifierRepoFactory;
import org.refact4j.eom.model.impl.EntityDescriptorImpl;
import org.refact4j.expr.Expression;

public final class EntityDescriptorBuilder {
    private EntityDescriptorImpl entityDescriptor;

    private EntityDescriptorBuilder() {
    }

    private EntityDescriptorBuilder(String name) {
        entityDescriptor = new EntityDescriptorImpl(name);
    }

    public static EntityDescriptorBuilder init(String name) {
        return new EntityDescriptorBuilder(name);
    }

    public EntityDescriptor getEntityDescriptor() {
        return entityDescriptor;
    }

    public EntityDescriptorBuilder addField(Field field) {
        this.entityDescriptor.addField(field);
        return this;
    }

    public EntityDescriptorBuilder addKeyField(Field field) {
        this.entityDescriptor.addKeyField(field);
        return this;
    }

    public EntityDescriptorBuilder addProperty(Object key, Object value) {
        this.entityDescriptor.addProperty(key, value);
        return this;
    }

    public EntityDescriptorBuilder setConstraint(Expression expression) {
        this.entityDescriptor.setConstraintExpression(expression);
        return this;
    }

    private EntityDescriptorBuilder setEntityStringifier(EntityStringifier entityObjectStringifierFunctor) {
        this.entityDescriptor.setEntityStringifier(entityObjectStringifierFunctor);
        return this;
    }

    public EntityDescriptorBuilder setBeanClass(Class<?> beanClass) {
        this.entityDescriptor.setBeanClass(beanClass);
        return this;
    }

    public EntityDescriptorBuilder setXmlEntityStringifier(String xmlStringifier) {
        setEntityStringifier(EntityStringifierRepoFactory.init(this.entityDescriptor,
                "<dataset><stringifiers>" + xmlStringifier + "</stringifiers>" + "</dataset>")
                .createEntityStringifierRepository().get(this.entityDescriptor));
        return this;
    }
}
