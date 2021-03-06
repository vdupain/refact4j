package org.refact4j.eom.model;

import org.refact4j.eom.model.impl.EntityDescriptorImpl;
import org.refact4j.expr.Expression;

public final class EntityDescriptorBuilder {
    private final EntityDescriptorImpl entityDescriptor;

    private EntityDescriptorBuilder(String name) {
        entityDescriptor = new EntityDescriptorImpl(name);
    }


    public static EntityDescriptorBuilder init(String name) {
        return new EntityDescriptorBuilder(name);
    }

    public EntityDescriptor get() {
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

    public EntityDescriptorBuilder setConstraint(Expression expression) {
        this.entityDescriptor.setConstraintExpression(expression);
        return this;
    }

}
