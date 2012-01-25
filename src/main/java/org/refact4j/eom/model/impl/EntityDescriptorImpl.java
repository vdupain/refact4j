package org.refact4j.eom.model.impl;

import org.refact4j.collection.FilterIterator;
import org.refact4j.eom.EOMContext;
import org.refact4j.eom.EntityExpression;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.model.DataField;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Property;
import org.refact4j.eom.model.RelationField;
import org.refact4j.functor.UnaryPredicate;
import org.refact4j.visitor.Visitor;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EntityDescriptorImpl implements EntityDescriptor {
    private String name;

    private Map<String, Field> fields = new TreeMap<String, Field>();
    private final List<Field> orderedFields = new ArrayList<Field>();

    private List<Field> keyFields = new ArrayList<Field>();

    private EntityExpression constraintExpression;

    private final Property property = new PropertyImpl();

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

    public void addField(Field field) {
        this.fields.put(field.getName(), field);
        this.orderedFields.add(field);
    }

    public void addKeyField(Field field) {
        this.keyFields.add(field);
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
        List<DataField> result = new ArrayList<DataField>();
        FilterIterator<Field> filterIterator = new FilterIterator<Field>(this.orderedFields.iterator(),
                new UnaryPredicate<Field>() {
                    public Boolean eval(Field field) {
                        return field instanceof DataField;
                    }
                });

        while (filterIterator.hasNext()) {
            result.add((DataField) filterIterator.next());
        }
        return result;
    }

    public List<RelationField> getRelationFields() {
        List<RelationField> result = new ArrayList<RelationField>();
        FilterIterator<Field> filterIterator = new FilterIterator<Field>(this.orderedFields.iterator(),
                new UnaryPredicate<Field>() {
                    public Boolean eval(Field field) {
                        return field instanceof RelationField;
                    }
                });
        while (filterIterator.hasNext()) {
            result.add((RelationField) filterIterator.next());
        }
        return result;
    }

    public boolean containsField(Field field) {
        return this.fields.containsValue(field);
    }

    public void setConstraintExpression(EntityExpression constraintExpression) {
        this.constraintExpression = constraintExpression;
    }

    public EntityExpression getConstraintExpression() {
        return this.constraintExpression;
    }

    public String toString() {
        return EntityDescriptorStringifier.DEFAULT.stringify(this);
    }

    public void addProperty(Object key, Object value) {
        this.property.addProperty(key, value);
    }

    public Object getProperty(Object key) {
        return this.property.getProperty(key);
    }

    public Property getProperty() {
        return this.property;
    }

    public void accept(Visitor visitor) {
        if (visitor instanceof EntityDescriptorVisitor) {
            ((EntityDescriptorVisitor) visitor).visitEntityDescriptor(this);
        }
    }

    public void setEntityStringifier(EntityStringifier entityStringifier) {
        this.entityStringifier = entityStringifier;
    }

    public EntityStringifier getEntityStringifier() {
        return entityStringifier;
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
        return EntityDescriptorStringifier.XML.stringify(this);
    }

    public EntityObject toEntity() {
        return EntityObjectBuilder.init(EntityDescriptorDesc.INSTANCE).set(EntityDescriptorDesc.NAME, this.name)
                .getEntity();
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
