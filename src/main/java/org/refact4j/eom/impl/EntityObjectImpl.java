package org.refact4j.eom.impl;

import org.refact4j.eom.*;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.impl.Stringifiers;
import org.refact4j.xml.XML;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.*;

/**
 * Default implementation of EntityObject interface.
 *
 * @see EntityObject static final
 */
public class EntityObjectImpl implements EntityObject {

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private final EntityObjectEventSupport entityObjectEventSupport = new EntityObjectEventSupport(this);
    private EntityStringifier stringifier = EntityStringifier.XML;
    private EntityDescriptor entityDescriptor;
    private Map<Field, Object> values = new HashMap<>();
    private Map<Field, Object> initialValues = new HashMap<>();

    public EntityObjectImpl() {
    }

    public EntityObjectImpl(EntityDescriptor entityDescriptor) {
        this(entityDescriptor, new HashMap<>());
    }

    private EntityObjectImpl(EntityDescriptor entityDescriptor, Map<Field, Object> values) {
        this.entityDescriptor = entityDescriptor;
        this.values = values;
        this.setStringifier(this.entityDescriptor.getEntityStringifier());
        this.initialValues = this.values;
        this.changeSupport.addPropertyChangeListener(new EntityObjectChangeSetDeltaPropertyChangeListener(entityObjectEventSupport));
    }

    public Iterator<Field> iterator() {
        return Collections.unmodifiableMap(this.values).keySet().iterator();
    }

    @Override
    public Object getFieldValue(Field field) {
        return this.values.get(field);
    }


    public EntityDescriptor getEntityDescriptor() {
        return this.entityDescriptor;
    }

    private void checkField(Field field) {
        if (!this.getEntityDescriptor().containsField(field)) {
            throw new RuntimeException("Object type '" + this.entityDescriptor.getName() + "' does not contain field '"
                    + field.getEntityDescriptor().getName() + "." + field.getName() + "'");
        }
    }


    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getKey() == null) ? 0 : this.getKey().hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof EntityObject))
            return false;
        final EntityObject other = (EntityObject) obj;
        return this.getKey().equals(other.getKey());
    }

    public void setStringifier(EntityStringifier stringifierFunctor) {
        this.stringifier = stringifierFunctor;
        if (this.stringifier == null) {
            this.stringifier = EntityStringifier.XML;
        }
    }

    public String toString() {
        return this.stringifier.stringify(this);
    }

    public String toXmlString() {
        return Stringifiers.ENTITY_XML.stringify(this);
    }

    public XML toXML() {
        return XML.DEFAULT.convert(this);
    }

    public Object clone() {
        return new EntityObjectImpl(this.entityDescriptor, this.values);
    }

    public void accept(EntityObjectVisitor visitor) {
        visitor.visitEntityObject(this);
    }

    public EntityObject set(Field field, Object value) {
        checkField(field);
        Object oldValue = this.values.put(field, value);
        this.firePropertyChange(field, oldValue, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.entityDescriptor = (EntityDescriptor) in.readObject();
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            String fieldName = (String) in.readObject();
            Object value = in.readObject();
            this.set(fieldName, value);
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.entityDescriptor);
        Set<Field> keySet = this.values.keySet();
        out.writeInt(keySet.size());
        keySet.stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(f -> {
                    try {
                        out.writeObject(f.getName());
                        out.writeObject(this.get(f));
                    } catch (IOException ignored) {
                    }
                });
    }

    private void firePropertyChange(Field field, Object oldValue, Object newValue) {
        if (!initialValues.containsKey(field)) {
            initialValues.put(field, oldValue);
        }
        changeSupport.firePropertyChange(field.getName(), oldValue, newValue);
    }

    public void registerListener(EntityObjectListener listener) {
        entityObjectEventSupport.registerListener(listener);
    }

    public void unregisterListener(EntityObjectListener listener) {
        entityObjectEventSupport.unregisterListener(listener);
    }
}
