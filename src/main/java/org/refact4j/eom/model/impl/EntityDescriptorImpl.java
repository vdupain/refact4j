package org.refact4j.eom.model.impl;

import org.refact4j.eom.EOMContext;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.model.DataField;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.RelationField;
import org.refact4j.visitor.Visitor;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.*;
import java.util.stream.Collectors;

public class EntityDescriptorImpl implements EntityDescriptor {
    private final List<Field> orderedFields = new ArrayList<>();
    private String name;
    private Map<String, Field> fields = new TreeMap<>();
    private List<Field> keyFields = new ArrayList<>();
    private EntityStringifier entityStringifier;

    private Class<?> beanClass;

    private EOMContext context;

    public EntityDescriptorImpl() {
    }

    public EntityDescriptorImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public EntityDescriptorImpl addField(Field field) {
        this.fields.put(field.getName(), field);
        this.orderedFields.add(field);
        return this;
    }

    public EntityDescriptorImpl addKeyField(Field field) {
        this.keyFields.add(field);
        return this;
    }

    public Collection<Field> getFields() {
        return this.fields.values();
    }

    public Field getField(String fieldName) {
        if (fieldName != null) {
            return this.fields.get(fieldName);
        }
        return null;
    }

    public List<Field> getKeyFields() {
        return this.keyFields;
    }

    public List<DataField> getAttributeFields() {
        return this.orderedFields
                .stream()
                .filter(p -> p instanceof DataField)
                .map(p -> (DataField) p)
                .collect(Collectors.toList());
    }

    public List<RelationField> getRelationFields() {
        return this.orderedFields
                .stream()
                .filter(p -> p instanceof RelationField)
                .map(p -> (RelationField) p)
                .collect(Collectors.toList());
    }

    public boolean containsField(Field field) {
        return this.fields.containsValue(field);
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof EntityDescriptorVisitor) {
            ((EntityDescriptorVisitor) visitor).visitEntityDescriptor(this);
        }
    }

    public EntityStringifier getEntityStringifier() {
        return entityStringifier;
    }

    public void setEntityStringifier(EntityStringifier entityStringifier) {
        this.entityStringifier = entityStringifier;
    }

    public Class<?> getBeanClass() {
        return this.beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public EOMContext getContext() {
        return this.context;
    }

    public void setContext(EOMContext context) {
        this.context = context;
    }

    public String toXmlString() {
        return Stringifiers.ENTITY_DESCRIPTOR_XML.stringify(this);
    }

    public String toString() {
        return Stringifiers.ENTITY_DESCRIPTOR_NAME.stringify(this);
    }


    public EntityObject toEntity() {
        return EntityObjectBuilder.init(EntityDescriptorDesc.INSTANCE).set(EntityDescriptorDesc.NAME, this.name)
                .get();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final EntityDescriptor other = (EntityDescriptor) obj;
        return this.getName().equals(other.getName());
    }

    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = (String) in.readObject();
        this.fields = (Map<String, Field>) in.readObject();
        this.keyFields = (List<Field>) in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.name);
        out.writeObject(this.fields);
        out.writeObject(this.keyFields);
    }

}
