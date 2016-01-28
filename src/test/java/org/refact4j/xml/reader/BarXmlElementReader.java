package org.refact4j.xml.reader;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.impl.EntityDataSet;
import org.refact4j.model.BarDesc;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.XmlHelper;

public class BarXmlElementReader extends DefaultXmlElementReader {

    public BarXmlElementReader(XmlAttributes xmlAttrs, DatasetConverterHolder datasetConverterHolder) {
        super(datasetConverterHolder);
        EntityObject barEntity;
        String name = XmlHelper.getAttrValue("name", xmlAttrs);
        barEntity = ((EntityDataSet) datasetConverterHolder.getDataSet()).getEntityByName(BarDesc.INSTANCE,
                BarDesc.NAME, name);
        if (barEntity == null) {
            barEntity = EntityObjectBuilder.init(BarDesc.INSTANCE).getEntity();
        }
        barEntity.set(BarDesc.ID, (Integer) ConverterHelper.convertString2Value(XmlHelper.getAttrValue("id", xmlAttrs),
                BarDesc.ID));
        barEntity.set(BarDesc.NAME, name);
        this.add(barEntity);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals("foo")) {
            return new FooXmlElementReader(attributes, this);
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }

}
