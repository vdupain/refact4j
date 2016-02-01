package org.refact4j.eom.impl;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectListener;
import org.refact4j.eom.model.*;
import org.refact4j.xml.XML;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class EntityObjectDecorator implements EntityObject {

    private final EntityObject entityObject;

    public EntityObjectDecorator(EntityObject entityObject) {
        this.entityObject = entityObject;
    }

    public void accept(EntityObjectVisitor visitor) {
        this.entityObject.accept(visitor);
    }

    public void checkConstraint() {
        this.entityObject.checkConstraint();
    }

    public void checkValues() {
        this.entityObject.checkValues();
    }

    public Object clone() throws CloneNotSupportedException {
        super.clone();
        return new EntityObjectDecorator((EntityObject) this.entityObject.clone());
    }

    public Boolean get(BooleanField booleanField) {
        return this.entityObject.get(booleanField);
    }

    public Date get(DateField dateField) {
        return this.entityObject.get(dateField);
    }

    public Double get(DoubleField doubleField) {
        return this.entityObject.get(doubleField);
    }

    public Object get(Field field) {
        return this.entityObject.get(field);
    }

    public Integer get(IntegerField integerField) {
        return this.entityObject.get(integerField);
    }

    public Object get(String fieldName) {
        return this.entityObject.get(fieldName);
    }

    public Object getFieldValue(Field field) {
        return this.entityObject.getFieldValue(field);
    }

    public String get(StringField stringField) {
        return this.entityObject.get(stringField);
    }

    public Key get(ManyToOneRelationField manyToOneRelationField) {
        return this.entityObject.get(manyToOneRelationField);
    }

    public Key get(OneToOneRelationField oneToOneRelationField) {
        return this.entityObject.get(oneToOneRelationField);
    }

    public Key getKey() {
        return this.entityObject.getKey();
    }

    public EntityDescriptor getEntityDescriptor() {
        return this.entityObject.getEntityDescriptor();
    }

    public Iterator<Field> iterator() {
        return this.entityObject.iterator();
    }

    public EntityObject set(BooleanField booleanField, Boolean booleanValue) {
        return this.entityObject.set(booleanField, booleanValue);
    }

    public EntityObject set(DateField dateField, Date dateValue) {
        return this.entityObject.set(dateField, dateValue);
    }

    public EntityObject set(DoubleField doubleField, Double doubleValue) {
        return this.entityObject.set(doubleField, doubleValue);
    }

    public EntityObject set(Field field, Object value) {
        return this.entityObject.set(field, value);
    }

    public EntityObject set(IntegerField integerField, Integer integerValue) {
        return this.entityObject.set(integerField, integerValue);
    }

    public EntityObject set(String fieldName, Object value) {
        return this.entityObject.set(fieldName, value);
    }

    public EntityObject set(StringField stringField, String stringValue) {
        return this.entityObject.set(stringField, stringValue);
    }

    public EntityObject set(OneToManyRelationField field, Collection<EntityObject> entityObjects) {
        return this.entityObject.set(field, entityObjects);
    }

    public EntityObject set(ManyToOneRelationField manyToOneRelationField, Key key) {
        return this.entityObject.set(manyToOneRelationField, key);
    }

    public EntityObject set(OneToOneRelationField oneToOneRelationField, Key key) {
        return this.entityObject.set(oneToOneRelationField, key);
    }

    public EntityObject set(ManyToOneRelationField manyToOneRelationField, EntityObject entityObject) {
        return this.entityObject.set(manyToOneRelationField, entityObject);
    }

    public EntityObject set(OneToOneRelationField oneToOneRelationField, EntityObject entityObject) {
        return this.entityObject.set(oneToOneRelationField, entityObject);
    }

    public String toXmlString() {
        return this.entityObject.toXmlString();
    }

    public XML toXML() {
        return this.entityObject.toXML();
    }

    public boolean equals(Object obj) {
        return this.entityObject.equals(obj);
    }

    public int hashCode() {
        return this.entityObject.hashCode();
    }

    public String toString() {
        return this.entityObject.toString();
    }

    public Key getId() {
        return this.entityObject.getId();
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.entityObject.readExternal(in);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        this.entityObject.writeExternal(out);
    }

    public void registerListener(EntityObjectListener listener) {
        this.entityObject.registerListener(listener);
    }

    public void unregisterListener(EntityObjectListener listener) {
        this.entityObject.unregisterListener(listener);
    }
}
