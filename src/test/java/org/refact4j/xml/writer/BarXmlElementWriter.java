package org.refact4j.xml.writer;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.FieldNameComparator;
import org.refact4j.model.BarDesc;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BarXmlElementWriter extends AbstractXmlElementWriter {

    public BarXmlElementWriter(DataSetConverterHolder holder) {
        super("bar", holder.getDataSet().getAll(BarDesc.INSTANCE), holder);
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        EntityObject dummy = (EntityObject) next();
        List<Field> fields = new ArrayList<>(dummy.getEntityDescriptor().getFields());
        Collections.sort(fields, new FieldNameComparator());
        for (Field field : fields) {
            xmlWriter.writeAttribute(field.getName(), ConverterHelper.convertValue2String(dummy.get(field), field));
        }
        return new XmlElementHandler[0];
    }


}
