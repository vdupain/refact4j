package org.refact4j.eom.xml.writer;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class EntityXmlNodeWriter implements XmlElementHandler {
    private final Iterator<EntityObject> iterator;

    private EntityObject entityObject;

    public EntityXmlNodeWriter(EntityDescriptorRepository entityDescriptorRepository, DataSetConverterHolder holder) {
        Collection<EntityObject> entityObjects = new EntityListImpl();
        StreamSupport.stream(entityDescriptorRepository.spliterator(), false)
            .forEach(e -> entityObjects.addAll(holder.getDataSet().getAll(e)));
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
        entityObject.getEntityDescriptor().getFields().stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(field -> xmlWriter.writeAttribute(field.getName(), ConverterHelper.convertValue2String(entityObject.get(field), field)));
        return new XmlElementHandler[0];
    }


}
