package org.refact4j.test;

import org.refact4j.test.reader.TestSuiteXmlElementReader;
import org.refact4j.xml.*;
import org.refact4j.xml.reader.DefaultXmlElementReader;

import java.util.Collection;
import java.util.Collections;

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
        return Collections.singletonList(factory);
    }

    public XmlElementHandler[] getXmlElementHandlers(DatasetConverterHolder holder) {
        return null;
    }

}
