package org.refact4j.eom.metamodel.xml.writer;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityPredicate;
import org.refact4j.eom.metamodel.EntityStringifierAppenderDesc;
import org.refact4j.eom.metamodel.FieldDesc;
import org.refact4j.eom.model.Key;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;
import org.refact4j.xml.writer.AbstractXmlElementWriter;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class EntityStringifierAppenderXmlNodeWriter extends AbstractXmlElementWriter {

    public EntityStringifierAppenderXmlNodeWriter(DatasetConverterHolder holder, EntityObject stringifierEntity) {
        super(EntityStringifierAppenderDesc.INSTANCE.getName(),
                (Collection<?>) holder.getDataSet().getAll(EntityStringifierAppenderDesc.INSTANCE).stream()
                .filter(filterEntityStringifierAppenders(stringifierEntity.getKey()))
                        .collect(Collectors.toList()),
                holder);
    }

    private static Predicate<EntityObject> filterEntityStringifierAppenders(final Key key) {
        return arg -> key.equals(arg.get(EntityStringifierAppenderDesc.STRINGIFIER));
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        EntityObject stringifierAppenderEntity = (EntityObject) next();
        xmlWriter.writeAttribute(EntityStringifierAppenderDesc.ID.getName(), stringifierAppenderEntity.get(
                EntityStringifierAppenderDesc.ID).toString());
        xmlWriter.writeAttribute(EntityStringifierAppenderDesc.STRING.getName(), stringifierAppenderEntity
                .get(EntityStringifierAppenderDesc.STRING));
        Key keyField = stringifierAppenderEntity.get(EntityStringifierAppenderDesc.FIELD);
        if (keyField != null) {
            xmlWriter.writeAttribute(EntityStringifierAppenderDesc.FIELD.getName(), keyField.getFieldValue(FieldDesc.NAME)
                    .toString());
        }

        return new XmlElementHandler[0];
    }


}
