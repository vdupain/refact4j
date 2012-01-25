package org.refact4j.test.reader;

import org.refact4j.test.XmlTestCase;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.reader.DefaultXmlElementReader;

public class TestCaseXmlElementReader extends DefaultXmlElementReader {
    private final XmlTestCase testCase = new XmlTestCase();

    public TestCaseXmlElementReader(XmlAttributes attributes, TestSuiteXmlElementReader testSuiteXmlElementReader) {
        super(testSuiteXmlElementReader);
        String name = attributes.getValue("name");
        testCase.setName(name);
        testSuiteXmlElementReader.getTestSuite().addTest(testCase);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals("before")) {
            return new BeforeTestXmlElementReader(this, attributes, testCase);
        }
        if (localName.equals("after")) {
            return new AfterTestXmlElementReader(this, attributes, testCase);
        }
        if (localName.equals("test")) {
            return new TestXmlElementReader(this, attributes, testCase);
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }
}
