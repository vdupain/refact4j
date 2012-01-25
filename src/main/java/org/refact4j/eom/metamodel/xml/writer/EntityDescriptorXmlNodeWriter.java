package org.refact4j.eom.metamodel.xml.writer;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;
import org.refact4j.xml.writer.AbstractXmlElementWriter;

public class EntityDescriptorXmlNodeWriter extends AbstractXmlElementWriter {

    private final EntityDescriptorRepository repository;

    public EntityDescriptorXmlNodeWriter(EntityDescriptorRepository repository, DatasetConverterHolder holder) {
        super(EntityDescriptorDesc.INSTANCE.getName(), holder.getDataSet().getAll(EntityDescriptorDesc.INSTANCE),
                holder);
        this.repository = repository;
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        EntityObject entityDescEntity = (EntityObject) next();
        xmlWriter.writeAttribute(EntityDescriptorDesc.NAME.getName(), entityDescEntity.get(EntityDescriptorDesc.NAME));

        return new XmlElementHandler[]{new FieldXmlNodeWriter(this, entityDescEntity),
                new PropertyXmlNodeWriter(this, entityDescEntity, this.repository)};
    }
}
