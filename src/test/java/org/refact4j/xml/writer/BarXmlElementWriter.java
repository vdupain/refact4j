package org.refact4j.xml.writer;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.model.BarDesc;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;

public class BarXmlElementWriter extends AbstractXmlElementWriter {

    public BarXmlElementWriter(DataSetConverterHolder holder) {
        super("bar", holder.getDataSet().getAll(BarDesc.INSTANCE), holder);
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        EntityObject entityObject = (EntityObject) next();
        entityObject.getEntityDescriptor().getFields().stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(f -> xmlWriter.writeAttribute(f.getName(), ConverterHelper.convertValue2String(entityObject.get(f), f)));
        return new XmlElementHandler[0];
    }


}
