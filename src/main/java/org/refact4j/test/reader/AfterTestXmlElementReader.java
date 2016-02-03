package org.refact4j.test.reader;

import org.refact4j.test.AfterTestHandler;
import org.refact4j.test.XmlTestCase;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.reader.DefaultXmlElementReader;

class AfterTestXmlElementReader extends DefaultXmlElementReader {

    public AfterTestXmlElementReader(DataSetConverterHolder dataSetConverterHolder, XmlAttributes attributes,
                                     XmlTestCase xmlTestCase) {
        super(dataSetConverterHolder);
        String handlerClassName = attributes.getValue("handler");
        try {
            Class<AfterTestHandler> handlerClass = (Class<AfterTestHandler>) Class.forName(handlerClassName);
            xmlTestCase.setAfterTestFunctor(handlerClass.newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
