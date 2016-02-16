package org.refact4j.eom.metamodel.xml.writer;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;
import org.refact4j.xml.writer.AbstractXmlElementWriter;

import java.util.stream.Collectors;

public class EntityDescriptorXmlNodeWriter extends AbstractXmlElementWriter {

    public EntityDescriptorXmlNodeWriter(EntityDescriptorRepository repository, DatasetConverterHolder holder) {
        super(EntityDescriptorDesc.INSTANCE.getName(), ((java.util.Set<EntityObject>) holder.getDataSet()).stream()
                        .filter(p -> p.getEntityDescriptor().equals(EntityDescriptorDesc.INSTANCE))
                        .collect(Collectors.toList()),
                holder);
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        EntityObject entityDescEntity = (EntityObject) next();
        xmlWriter.writeAttribute(EntityDescriptorDesc.NAME.getName(), entityDescEntity.get(EntityDescriptorDesc.NAME));
        return new XmlElementHandler[]{new FieldXmlNodeWriter(this, entityDescEntity)};
    }
}
