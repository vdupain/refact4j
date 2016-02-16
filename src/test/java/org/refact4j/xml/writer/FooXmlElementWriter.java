package org.refact4j.xml.writer;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.impl.EntityList;
import org.refact4j.eom.model.Key;
import org.refact4j.model.BarDesc;
import org.refact4j.model.FooDesc;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;

import java.util.stream.Collectors;

public class FooXmlElementWriter extends AbstractXmlElementWriter {

    public FooXmlElementWriter(DatasetConverterHolder holder) {
        super("foo", ((java.util.Set<EntityObject>) holder.getDataSet()).stream()
                .filter(p -> p.getEntityDescriptor().equals(FooDesc.INSTANCE))
                .collect(Collectors.toList()), holder);
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        EntityObject dummy = (EntityObject) next();
        xmlWriter.writeAttribute(FooDesc.ID.getName(), dummy.get(FooDesc.ID).toString());
        xmlWriter.writeAttribute(FooDesc.NAME.getName(), dummy.get(FooDesc.NAME));
        Key parent = dummy.get(FooDesc.BAR);
        if (parent != null) {
            EntityList list = new EntityList(this.getDataSet());
            EntityObject parentEntity = list.stream()
                    .filter(p -> p.getKey().equals(parent))
                    .findFirst().get();
            if (parentEntity != null) {
                xmlWriter.writeAttribute("parent", parentEntity.get(BarDesc.NAME));
            }
        }
        return new XmlElementHandler[0];
    }


}
