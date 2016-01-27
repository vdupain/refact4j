package org.refact4j.test.reader;

import org.refact4j.test.TestMethod;
import org.refact4j.test.TestMethodHandler;
import org.refact4j.test.XmlTestCase;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.reader.DefaultXmlElementReader;

public class TestXmlElementReader extends DefaultXmlElementReader {
    private final TestMethod testMethod;

    public TestXmlElementReader(DatasetConverterHolder datasetConverterHolder, XmlAttributes attributes,
                                XmlTestCase xmlTestCase) {
        super(datasetConverterHolder);
        final String testName = attributes.getValue("name");
        testMethod = new TestMethod();
        testMethod.setName(testName);

        TestMethodHandler testMethodHandler = new TestMethodHandler() {

            public Object apply(XmlTestCase arg) {
                System.out.println("TestMethodHandler.apply()=" + testName);
                return null;
            }
        };
        testMethod.setTestMethodHandler(testMethodHandler);
        xmlTestCase.addTestMethod(testMethod);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals("assertEquals")) {
            return new AssertEqualsXmlElementReader(this, attributes, testMethod);
        } else if (localName.equals("assertTrue")) {
            return new AssertTrueXmlElementReader(this, attributes, testMethod);
        } else if (localName.equals("assertFalse")) {
            return new AssertFalseXmlElementReader(this, attributes, testMethod);
        }

        return super.createChildXmlElement(localName, qName, attributes);
    }

}
