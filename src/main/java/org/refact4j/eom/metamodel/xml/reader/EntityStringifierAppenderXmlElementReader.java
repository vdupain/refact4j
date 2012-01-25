package org.refact4j.eom.metamodel.xml.reader;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.metamodel.EntityStringifierAppenderDesc;
import org.refact4j.eom.metamodel.EntityStringifierDesc;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlHelper;
import org.refact4j.xml.reader.DefaultXmlElementReader;

class EntityStringifierAppenderXmlElementReader extends DefaultXmlElementReader {

    public EntityStringifierAppenderXmlElementReader(XmlAttributes xmlAttrs, DatasetConverterHolder datasetConverterHolder,
                                                     EntityObject stringifierEntity, EntityDescriptorRepository repository) {
        super(datasetConverterHolder);
        EntityObject stringifierAppender = EntityObjectBuilder.init(EntityStringifierAppenderDesc.INSTANCE).getEntity();
        stringifierAppender.set(EntityStringifierAppenderDesc.ID, Integer.parseInt(XmlHelper.getAttrValue(
                EntityStringifierAppenderDesc.ID.getName(), xmlAttrs, null)));
        stringifierAppender.set(EntityStringifierAppenderDesc.STRING, XmlHelper.getAttrValue(
                EntityStringifierAppenderDesc.STRING.getName(), xmlAttrs, null));
        stringifierAppender.set(EntityStringifierAppenderDesc.STRINGIFIER, stringifierEntity);

        EntityDescriptor entityDescriptor = repository.getEntityDescriptor(stringifierEntity.get(
                EntityStringifierDesc.OBJECT_TYPE).getFieldValue(EntityDescriptorDesc.NAME).toString());
        String fieldName = XmlHelper.getAttrValue(EntityStringifierAppenderDesc.FIELD.getName(), xmlAttrs, null);
        if (fieldName != null) {
            EntityObject fieldEntity = entityDescriptor.getField(fieldName).toEntity();
            stringifierAppender.set(EntityStringifierAppenderDesc.FIELD, fieldEntity);
        }
        this.add(stringifierAppender);
    }

}
