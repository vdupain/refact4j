package org.refact4j.eom.metamodel.xml.writer;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.metamodel.DataTypeType;
import org.refact4j.eom.metamodel.EntityDescriptorDesc;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.Key;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;
import org.refact4j.xml.writer.AbstractXmlElementWriter;

import java.util.Collection;
import java.util.stream.Collectors;

class FieldXmlNodeWriter extends AbstractXmlElementWriter {

    public FieldXmlNodeWriter(DatasetConverterHolder holder, EntityObject entityDescEntity) {
        super(FieldDesc.INSTANCE.getName(), (Collection<?>) holder.getDataSet().getAll(FieldDesc.INSTANCE).stream()
                        .filter(FieldDesc.getAllFieldsForEntityDescriptor(entityDescEntity.get(EntityDescriptorDesc.NAME)))
                        .collect(Collectors.toList())
                , holder);
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        EntityObject fieldEntity = (EntityObject) next();
        xmlWriter.writeAttribute(FieldDesc.NAME.getName(), fieldEntity.get(FieldDesc.NAME));
        xmlWriter.writeAttribute(FieldDesc.PRETTY_NAME.getName(), fieldEntity.get(FieldDesc.PRETTY_NAME));
        xmlWriter.writeAttribute(FieldDesc.DEFAULT_VALUE.getName(), fieldEntity.get(FieldDesc.DEFAULT_VALUE));
        xmlWriter.writeAttribute(FieldDesc.MIN_VALUE.getName(), fieldEntity.get(FieldDesc.MIN_VALUE));
        xmlWriter.writeAttribute(FieldDesc.MAX_VALUE.getName(), fieldEntity.get(FieldDesc.MAX_VALUE));
        xmlWriter.writeAttribute(FieldDesc.IS_KEY.getName(), fieldEntity.get(FieldDesc.IS_KEY).toString());
        xmlWriter.writeAttribute(FieldDesc.DATA_TYPE.getName(), fieldEntity.get(FieldDesc.DATA_TYPE).getFieldValue(
                DataTypeType.NAME).toString());
        Key targetEntityDescriptorKey = fieldEntity.get(FieldDesc.TARGET);
        if (targetEntityDescriptorKey != null) {
            xmlWriter.writeAttribute(FieldDesc.TARGET.getName(), targetEntityDescriptorKey.getFieldValue(
                    EntityDescriptorDesc.NAME).toString());
            Key keyField = fieldEntity.get(FieldDesc.INVERSE_RELATION_FIELD);
            if (keyField != null) {
                xmlWriter.writeAttribute(FieldDesc.INVERSE_RELATION_FIELD.getName(), (String) keyField
                        .getFieldValue(FieldDesc.NAME));
            }
        }
        xmlWriter.writeAttribute(FieldDesc.NULLABLE.getName(), fieldEntity.get(FieldDesc.NULLABLE).toString());
        xmlWriter.writeAttribute(FieldDesc.VISIBLE.getName(), fieldEntity.get(FieldDesc.VISIBLE).toString());
        xmlWriter.writeAttribute(FieldDesc.EDITABLE.getName(), fieldEntity.get(FieldDesc.EDITABLE).toString());
        Integer order = fieldEntity.get(FieldDesc.ORDER);
        if (order != null) {
            xmlWriter.writeAttribute(FieldDesc.ORDER.getName(), order.toString());
        }
        Integer value = fieldEntity.get(FieldDesc.MAX_LENGTH);
        if (value != null) {
            xmlWriter.writeAttribute(FieldDesc.MAX_LENGTH.getName(), value.toString());
        }
        value = fieldEntity.get(FieldDesc.MIN_LENGTH);
        if (value != null) {
            xmlWriter.writeAttribute(FieldDesc.MIN_LENGTH.getName(), value.toString());
        }

        return new XmlElementHandler[0];
    }


}
