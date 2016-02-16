package org.refact4j.eom.metamodel.xml.writer;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.metamodel.EntityStringifierDesc;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;
import org.refact4j.xml.writer.AbstractXmlElementWriter;

import java.util.stream.Collectors;

public class EntityStringifierXmlNodeWrite extends AbstractXmlElementWriter {

    public EntityStringifierXmlNodeWrite(DatasetConverterHolder holder) {
        super(EntityStringifierDesc.INSTANCE.getName(), ((java.util.Set<EntityObject>) holder.getDataSet()).stream()
                        .filter(p -> p.getEntityDescriptor().equals(EntityStringifierDesc.INSTANCE))
                        .collect(Collectors.toList()),
                holder);
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        EntityObject stringifierEntity = (EntityObject) next();
        xmlWriter.writeAttribute(EntityStringifierDesc.NAME.getName(), stringifierEntity.get(EntityStringifierDesc.NAME));
        xmlWriter.writeAttribute(EntityStringifierDesc.OBJECT_TYPE.getName(), stringifierEntity.get(
                EntityStringifierDesc.OBJECT_TYPE).getFieldValue(EntityDescriptorDesc.NAME).toString());
        return new XmlElementHandler[]{new EntityStringifierAppenderXmlNodeWriter(this, stringifierEntity)};
    }
}
