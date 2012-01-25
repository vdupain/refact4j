package org.refact4j.test.reader;

import junit.framework.TestSuite;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.reader.DefaultXmlElementReader;

public class TestSuiteXmlElementReader extends DefaultXmlElementReader {

    private TestSuite testSuite;

    public TestSuiteXmlElementReader(DatasetConverterHolder datasetConverterHolder) {
        super(datasetConverterHolder);
        this.testSuite = new TestSuite("testSuite");
        this.add(this.testSuite);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals("testCase")) {
            return new TestCaseXmlElementReader(attributes, this);
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }

    public TestSuite getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(TestSuite testSuite) {
        this.testSuite = testSuite;
    }

}
