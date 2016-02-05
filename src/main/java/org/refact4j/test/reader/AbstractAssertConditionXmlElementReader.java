package org.refact4j.test.reader;

import org.refact4j.test.AssertConditionHandler;
import org.refact4j.test.TestMethod;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.reader.DefaultXmlElementReader;

public class AbstractAssertConditionXmlElementReader extends DefaultXmlElementReader {

    AbstractAssertConditionXmlElementReader(DatasetConverterHolder dataSetConverterHolder,
                                            XmlAttributes attributes, TestMethod testMethod, AssertConditionHandler assertConditionHandler) {
        super(dataSetConverterHolder);
        assertConditionHandler.setMessage(attributes.getValue("message"));
        assertConditionHandler.setCondition(Boolean.valueOf(attributes.getValue("condition")));

        testMethod.addAssertion(assertConditionHandler);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
//        if (localName.equals("condition")) {
//            return new DefaultXmlElementReader(this) {
//                @Override
//                public void setStringValue(String value) {
//                    assertCondition.setCondition(Boolean.valueOf(value));
//                }
//            };
//        }
        return super.createChildXmlElement(localName, qName, attributes);
    }

}
