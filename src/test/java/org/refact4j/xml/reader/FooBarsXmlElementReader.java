package org.refact4j.xml.reader;

import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;

public class FooBarsXmlElementReader extends DefaultXmlElementReader {

    public FooBarsXmlElementReader(DataSetConverterHolder dataSetConverterHolder) {
        super(dataSetConverterHolder);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals("bars")) {
            return new BarsXmlElementReader(this);
        }
        if (localName.equals("foos")) {
            return new FoosXmlElementReader(this);
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }
}
