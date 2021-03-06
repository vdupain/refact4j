package org.refact4j.xml.reader;

import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;

public class BarsXmlElementReader extends DefaultXmlElementReader {

    public BarsXmlElementReader(DatasetConverterHolder dataSetConverterHolder) {
        super(dataSetConverterHolder);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals("bar")) {
            return new BarXmlElementReader(attributes, this);
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }

}
