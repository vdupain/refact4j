package org.refact4j.test;

import org.refact4j.test.reader.TestSuiteXmlElementReader;
import org.refact4j.xml.*;
import org.refact4j.xml.reader.DefaultXmlElementReader;

import java.util.Arrays;
import java.util.Collection;

public class TestSuiteXmlDescriptor implements XmlDescriptor {

    private final XmlElementFactory factory = new XmlElementFactory() {

        public XmlElement createXmlElement(DefaultXmlElementReader xmlElement) {
            return new TestSuiteXmlElementReader(xmlElement);
        }

        public String getXmlElementTagName() {
            return "testSuite";
        }

    };

    public Collection<XmlElementFactory> getXmlElementFactories() {
        return Arrays.asList(factory);
    }

    public XmlElementHandler[] getXmlElementHandlers(DatasetConverterHolder holder) {
        return null;
    }

}
