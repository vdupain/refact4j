package org.refact4j.eom.xml.writer;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityCollection;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.impl.EntityList;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.FieldNameComparator;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class EntityXmlNodeWriter implements XmlElementHandler {
    private final Iterator<EntityObject> iterator;

    private EntityObject entityObject;

    public EntityXmlNodeWriter(EntityDescriptorRepository entityDescriptorRepository, DatasetConverterHolder holder) {
        EntityCollection entityObjects = new EntityList();
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
