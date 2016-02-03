package org.refact4j.eom.metamodel.xml.writer;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.metamodel.PropertyDesc;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;
import org.refact4j.xml.writer.AbstractXmlElementWriter;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class PropertyXmlNodeWriter extends AbstractXmlElementWriter {

    public PropertyXmlNodeWriter(DataSetConverterHolder holder, EntityObject entityDescEntity,
                                 EntityDescriptorRepository repository) {
        super(PropertyDesc.INSTANCE.getName(), (Collection<?>) holder.getDataSet().getAll(
                PropertyDesc.INSTANCE).stream()
                .filter(PropertyDesc.getPropertiesForEntityDescriptor(repository.getEntityDescriptor(entityDescEntity
                        .get(EntityDescriptorDesc.NAME)))).collect(Collectors.toList()), holder);
    }

    public PropertyXmlNodeWriter(DataSetConverterHolder holder, EntityObject fieldEntity) {
        super(PropertyDesc.INSTANCE.getName(), (Collection<?>) holder.getDataSet().getAll(PropertyDesc.INSTANCE).stream()
                .filter(getPropertiesForField(fieldEntity)).collect(Collectors.toList()), holder);

    }

    private static Predicate<EntityObject> getPropertiesForField(final EntityObject fieldEntity) {
        return arg -> {
            Key key = KeyBuilder.init(FieldDesc.INSTANCE).set(FieldDesc.NAME, fieldEntity.get(FieldDesc.NAME)).set(
                    FieldDesc.ENTITY_DESC, fieldEntity.get(FieldDesc.ENTITY_DESC)).getKey();
            return key.equals(arg.get(PropertyDesc.FIELD_TYPE));
        };
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        EntityObject property = (EntityObject) next();
        xmlWriter.writeAttribute(PropertyDesc.KEY.getName(), property.get(PropertyDesc.KEY));
        xmlWriter.writeAttribute(PropertyDesc.VALUE.getName(), property.get(PropertyDesc.VALUE));
        xmlWriter.writeAttribute(PropertyDesc.DATA_TYPE.getName(), property.get(PropertyDesc.DATA_TYPE).getFieldValue(
                DataTypeType.NAME).toString());

        return new XmlElementHandler[0];
    }

}
