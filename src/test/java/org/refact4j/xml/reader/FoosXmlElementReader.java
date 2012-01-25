package org.refact4j.xml.reader;

import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;

public class FoosXmlElementReader extends DefaultXmlElementReader {

    public FoosXmlElementReader(DatasetConverterHolder datasetConverterHolder) {
        super(datasetConverterHolder);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals("foo")) {
            return new FooXmlElementReader(attributes, this);
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }

}
