package org.refact4j.test.reader;

import org.refact4j.test.BeforeTestHandler;
import org.refact4j.test.XmlTestCase;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.reader.DefaultXmlElementReader;

class BeforeTestXmlElementReader extends DefaultXmlElementReader {

    public BeforeTestXmlElementReader(DataSetConverterHolder dataSetConverterHolder, XmlAttributes attributes,
                                      XmlTestCase xmlTestCase) {
        super(dataSetConverterHolder);
        String handlerClassName = attributes.getValue("handler");
        try {
            Class<BeforeTestHandler> handlerClass = (Class<BeforeTestHandler>) Class.forName(handlerClassName);
            xmlTestCase.setBeforeTestFunctor(handlerClass.newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
