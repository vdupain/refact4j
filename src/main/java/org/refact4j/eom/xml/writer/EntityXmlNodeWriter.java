package org.refact4j.eom.xml.writer;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.impl.EntityList;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class EntityXmlNodeWriter implements XmlElementHandler {
    private final Iterator<EntityObject> iterator;

    private EntityObject entityObject;

    public EntityXmlNodeWriter(EntityDescriptorRepository entityDescriptorRepository, DatasetConverterHolder holder) {
        Collection<EntityObject> entityObjects = new EntityList();
        java.util.Set<EntityObject> set = new HashSet<>(holder.getDataSet());
        StreamSupport.stream(entityDescriptorRepository.values().spliterator(), false)
                .forEach(e -> entityObjects.addAll(set.stream().filter(p -> p.getEntityDescriptor().equals(e)).collect(Collectors.toList())));
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
