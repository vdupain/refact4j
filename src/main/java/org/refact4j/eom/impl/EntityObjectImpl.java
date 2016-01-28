package org.refact4j.eom.impl;

import org.refact4j.eom.*;
import org.refact4j.eom.model.*;
import org.refact4j.expr.Expression;
import org.refact4j.xml.XML;
import org.refact4j.xml.XMLizer;

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

    public Key getKey() {
        KeyBuilder keyBuilder = KeyBuilder.init(this.entityDescriptor);
        for (Field field : this.entityDescriptor.getKeyFields()) {
            keyBuilder.set(field, get(field));
        }
        return keyBuilder.getKey();
    }

    public Iterator<Field> iterator() {
        return Collections.unmodifiableMap(this.values).keySet().iterator();
    }

    private Object getFieldValue(Field field) {
        return this.values.get(field);
    }

    public Object get(Field field) {
        return this.getFieldValue(field);
    }

    public Object get(String fieldName) {
        Field field = this.getEntityDescriptor().getField(fieldName);
        return this.getFieldValue(field);
    }

    public Integer get(IntegerField integerField) {
        return (Integer) this.getFieldValue(integerField);
    }

    public Double get(DoubleField doubleField) {
        return (Double) this.getFieldValue(doubleField);
    }

    public String get(StringField stringField) {
        return (String) this.getFieldValue(stringField);
    }

    public Date get(DateField dateField) {
        return (Date) this.getFieldValue(dateField);
    }

    public Boolean get(BooleanField booleanField) {
        return (Boolean) this.getFieldValue(booleanField);
    }

    public Key get(ManyToOneRelationField manyToOneRelationField) {
        return (Key) this.getFieldValue(manyToOneRelationField);
    }

    public Key get(OneToOneRelationField oneToOneRelationField) {
        return (Key) this.getFieldValue(oneToOneRelationField);
    }

    public void setFieldValue(Field field, Object value) {
        checkField(field);
        Object oldValue = this.values.put(field, value);
        this.firePropertyChange(field, oldValue, value);
    }

    public EntityDescriptor getEntityDescriptor() {
        return this.entityDescriptor;
    }

    public void setEntityDescriptor(EntityDescriptor entityDescriptor) {
        this.entityDescriptor = entityDescriptor;
    }

    void checkField(Field field) {
        if (!this.entityDescriptor.containsField(field)) {
            throw new RuntimeException("Object type '" + this.entityDescriptor.getName() + "' does not contain field '"
                    + field.getEntityDescriptor().getName() + "." + field.getName() + "'");
        }
    }

    public void checkConstraint() {
        Expression constraint = this.getEntityDescriptor().getConstraintExpression();
        if (constraint != null && !constraint.apply(this)) {
            throw new RuntimeException("Constraint " + constraint + " failed: " + constraint.getPropertyName() + "="
                    + this.get(constraint.getPropertyName()));
        }
    }

    public void checkValues() {
        Collection<Field> fields = getEntityDescriptor().getFields();
        for (Field field : fields) {
            field.checkValue(get(field));
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
        return EntityStringifier.XML.stringify(this);
    }

    public XML toXML() {
        return XMLizer.DEFAULT.xml(this);
    }

    public Object clone() {
        return new EntityObjectImpl(this.entityDescriptor, this.values);
    }

    public void accept(EntityObjectVisitor visitor) {
        visitor.visitEntityObject(this);
    }

    public EntityObject set(StringField stringField, String stringValue) {
        this.setFieldValue(stringField, stringValue);
        return this;
    }

    public EntityObject set(IntegerField integerField, Integer integerValue) {
        this.setFieldValue(integerField, integerValue);
        return this;
    }

    public EntityObject set(DoubleField doubleField, Double doubleValue) {
        this.setFieldValue(doubleField, doubleValue);
        return this;
    }

    public EntityObject set(DateField dateField, Date dateValue) {
        this.setFieldValue(dateField, dateValue);
        return this;
    }

    public EntityObject set(BooleanField booleanField, Boolean booleanValue) {
        this.setFieldValue(booleanField, booleanValue);
        return this;
    }

    public EntityObject set(ManyToOneRelationField manyToOneRelationField, Key key) {
        this.setFieldValue(manyToOneRelationField, key);
        return this;
    }

    public EntityObject set(OneToOneRelationField oneToOneRelationField, Key key) {
        this.setFieldValue(oneToOneRelationField, key);
        return this;
    }

    public EntityObject set(ManyToOneRelationField manyToOneRelationField, EntityObject entityObject) {
        this.setFieldValue(manyToOneRelationField, entityObject != null ? entityObject.getKey() : null);
        return this;
    }

    public EntityObject set(OneToOneRelationField oneToOneRelationField, EntityObject entityObject) {
        this.setFieldValue(oneToOneRelationField, entityObject != null ? entityObject.getKey() : null);
        return this;
    }

    public EntityObject set(String fieldName, Object value) {
        Field field = this.getEntityDescriptor().getField(fieldName);
        if (field == null) {
            throw new RuntimeException("Entity '" + this.getEntityDescriptor().getName() + "' does not contain field '"
                    + fieldName + "'");
        }
        this.setFieldValue(field, value);
        return this;
    }

    public EntityObject set(Field field, Object value) {
        this.setFieldValue(field, value);
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
        List<Field> keySet = new ArrayList<>(this.values.keySet());
        Collections.sort(keySet, new FieldNameComparator());
        out.writeInt(keySet.size());
        for (Field field : keySet) {
            out.writeObject(field.getName());
            out.writeObject(this.get(field));
        }
    }

    public Key getId() {
        return this.getKey();
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
