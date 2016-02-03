package org.refact4j.eom.xml.writer;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.FieldNameComparator;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;

import java.util.*;

public class EntityXmlNodeWriter implements XmlElementHandler {
    private final Iterator<EntityObject> iterator;

    private EntityObject entityObject;

    public EntityXmlNodeWriter(EntityDescriptorRepository entityDescriptorRepository, DataSetConverterHolder holder) {
        Collection<EntityObject> entityObjects = new EntityListImpl();
        for (EntityDescriptor entityDescriptor : entityDescriptorRepository) {
            entityObjects.addAll(holder.getDataSet().getAll(entityDescriptor));
        }
        iterator = entityObjects.iterator();
    }

    private String getNextTagName() {
        entityObject = iterator.next();
        return entityObject.getEntityDescriptor().getName();
    }

    public String getTagName() {
        return this.getNextTagName();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        List<Field> fields = new ArrayList<>(entityObject.getEntityDescriptor().getFields());
        Collections.sort(fields, new FieldNameComparator());
        for (Field field : fields) {
            xmlWriter.writeAttribute(field.getName(), ConverterHelper.convertValue2String(entityObject.get(field), field));
        }
        return new XmlElementHandler[0];
    }


}
