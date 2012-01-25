package org.refact4j.eom;

import org.refact4j.eom.metamodel.EntityStringifierDesc;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.xml.writer.EntityXmlWriterHelper;
import org.refact4j.functor.AbstractStringifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * EntityStringifier is the default implementation of EntityObject Stringifier.
 */
public class EntityStringifier extends AbstractStringifier<EntityObject> implements EntityFunctor<String>, ToEntity {

    public static final EntityStringifier DEFAULT = new EntityStringifier() {
        public String stringify(EntityObject entityObject) {
            StringBuffer strBuf = new StringBuffer();
            List<Field> keyFields = entityObject.getEntityDescriptor().getKeyFields();
            for (Iterator<Field> iter = keyFields.iterator(); iter.hasNext();) {
                Field field = iter.next();
                strBuf.append(entityObject.get(field));
                if (iter.hasNext()) {
                    strBuf.append(" - ");
                }

            }
            return strBuf.toString();
        }
    };

    public static final EntityStringifier XML = new EntityStringifier() {
        public String stringify(EntityObject entityObject) {
            return EntityXmlWriterHelper.dump(entityObject);
        }
    };

    private final List<EntityObject> appenders = new ArrayList<EntityObject>();

    private String name;

    private EntityDescriptor entityDescriptor;

    public EntityStringifier() {

    }

    public List<EntityObject> getAppenders() {
        return appenders;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEntityDescriptor(EntityDescriptor entityDescriptor) {
        this.entityDescriptor = entityDescriptor;
    }

    public EntityObject toEntity() {
        return EntityObjectBuilder.init(EntityStringifierDesc.INSTANCE).set(EntityStringifierDesc.OBJECT_TYPE,
                entityDescriptor != null ? entityDescriptor.toEntity().getKey() : null).set(
                EntityStringifierDesc.NAME, name).getEntity();
    }

    @Override
    public String stringify(EntityObject arg) {
        StringBuffer s = new StringBuffer();
        for (EntityObject appender : appenders) {
            String string = (String) appender.get("string");
            if (string != null) {
                s.append(string);
            }
            Key keyField = (Key) appender.get("field");
            if (keyField != null) {
                String fieldName = (String) keyField.getFieldValue(keyField.getEntityDescriptor().getField("name"));
                Field field = arg.getEntityDescriptor().getField(fieldName);
                Object value = arg.get(field);
                s.append(ConverterHelper.convertValue2String(value, field));
            }
        }
        return s.toString();
    }
}
