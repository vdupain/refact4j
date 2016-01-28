package org.refact4j.eom.model.impl;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityExpression;
import org.refact4j.eom.EntityExpressionBuilder;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.util.ComparatorHelper;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class KeyImpl implements Key {
    private final Map<Field, Object> keys = new HashMap<>();

    private EntityDescriptor entityDescriptor;

    public KeyImpl() {
    }

    public KeyImpl(EntityDescriptor entityDescriptor) {
        this.entityDescriptor = entityDescriptor;
    }

    public Object getFieldValue(Field field) {
        return this.keys.get(field);
    }

    public EntityDescriptor getEntityDescriptor() {
        return this.entityDescriptor;
    }

    public void add(Field field, Object value) {
        if (!field.getEntityDescriptor().equals(entityDescriptor)) {
            throw new RuntimeException("Field " + field.getEntityDescriptor().getName() + "." + field.getName()
                    + " is not defined for type '" + entityDescriptor.getName() + "'");
        }
        if (!entityDescriptor.getKeyFields().contains(field)) {
            throw new RuntimeException("Field " + field + " is not part of the key");
        }
        this.keys.put(field, value);
    }

    public String toString() {
        if (entityDescriptor == null) {
            return super.toString();
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append(entityDescriptor.getName());
        buffer.append("[");
        List<Entry<Field, Object>> entrySet = new ArrayList<>(keys.entrySet());
        Comparator<Map.Entry<Field, Object>> comparator = (o1, o2) -> o1.getKey().getName().compareTo(o2.getKey().getName());

        Collections.sort(entrySet, comparator);
        for (Iterator<Map.Entry<Field, Object>> iter = entrySet.iterator(); iter.hasNext(); ) {
            Map.Entry<Field, Object> entry = iter.next();
            buffer.append(entry.getKey().getName());
            buffer.append('=');
            buffer.append(ConverterHelper.convertValue2String(entry.getValue(), entry.getKey()));
            if (iter.hasNext()) {
                buffer.append(',');
            }
        }
        buffer.append("]");
        return buffer.toString();
    }

    public int compareTo(Key key) {
        for (Field field : entityDescriptor.getKeyFields()) {
            int result = ComparatorHelper.compare(((Comparable<?>) getFieldValue(field)), ((Comparable<?>) key
                    .getFieldValue(field)));
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((keys == null) ? 0 : keys.hashCode());
        result = prime * result + ((entityDescriptor == null) ? 0 : entityDescriptor.hashCode());
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
        final KeyImpl other = (KeyImpl) obj;
        if (keys == null) {
            if (other.keys != null)
                return false;
        } else if (!keys.equals(other.keys))
            return false;
        if (entityDescriptor == null) {
            if (other.entityDescriptor != null)
                return false;
        } else if (!entityDescriptor.equals(other.entityDescriptor))
            return false;
        return true;
    }

    public boolean isCompound() {
        return keys.size() > 1;
    }

    public boolean isUnique() {
        return keys.size() == 1;
    }

    public EntityExpression asExpression() {
        EntityExpressionBuilder exprBuilder = null;
        for (Field keyField : this.getEntityDescriptor().getKeyFields()) {
            EntityExpressionBuilder tmpExp = EntityExpressionBuilder.init(keyField).equalTo(
                    this.getFieldValue(keyField));
            if (exprBuilder != null) {
                exprBuilder.and(tmpExp);
            } else {
                exprBuilder = tmpExp;
            }
        }
        return exprBuilder.getExpression();
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.entityDescriptor = (EntityDescriptor) in.readObject();
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            Field field = (Field) in.readObject();
            Object value = in.readObject();
            this.add(field, value);
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.entityDescriptor);
        Set<Field> keySet = this.keys.keySet();
        out.writeInt(keySet.size());
        for (Field field : keySet) {
            out.writeObject(field);
            out.writeObject(this.keys.get(field));
        }
    }

}
