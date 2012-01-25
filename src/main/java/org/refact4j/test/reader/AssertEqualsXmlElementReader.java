package org.refact4j.test.reader;

import org.refact4j.test.AssertEquals;
import org.refact4j.test.TestMethod;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.reader.DefaultXmlElementReader;

public class AssertEqualsXmlElementReader extends DefaultXmlElementReader {
    private final AssertEquals assertEquals;

    public AssertEqualsXmlElementReader(DatasetConverterHolder datasetConverterHolder, XmlAttributes attributes,
                                        TestMethod testMethod) {
        super(datasetConverterHolder);

        assertEquals = new AssertEquals();
        assertEquals.setMessage(attributes.getValue("message"));
        testMethod.addAssertion(assertEquals);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals("expected")) {
            return new DefaultXmlElementReader(this) {
                @Override
                public void setStringValue(String value) {
                    assertEquals.setExpected(value);
                }
            };
        }
        if (localName.equals("actual")) {
            return new DefaultXmlElementReader(this) {
                @Override
                public void setStringValue(String value) {
                    assertEquals.setActual(value);
                }
            };
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }

}
