package org.refact4j.xml.writer;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.Key;
import org.refact4j.model.BarDesc;
import org.refact4j.model.FooDesc;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;

public class FooXmlElementWriter extends AbstractXmlElementWriter {

    public FooXmlElementWriter(DatasetConverterHolder holder) {
        super("foo", holder.getDataSet().getAll(FooDesc.INSTANCE), holder);
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        EntityObject dummy = (EntityObject) next();
        xmlWriter.writeAttribute(FooDesc.ID.getName(), dummy.get(FooDesc.ID).toString());
        xmlWriter.writeAttribute(FooDesc.NAME.getName(), dummy.get(FooDesc.NAME));
        Key parent = dummy.get(FooDesc.BAR);
        if (parent != null) {
            EntityObject parentEntity = (EntityObject) this.getDataSet().findByIdentifier(parent);
            if (parentEntity != null) {
                xmlWriter.writeAttribute("parent", parentEntity.get(BarDesc.NAME));
            }
        }
        return new XmlElementHandler[0];
    }


}
