package org.refact4j.test;

import org.refact4j.test.reader.TestSuiteXmlElementReader;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlDescriptor;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.XmlElementFactory;
import org.refact4j.xml.XmlElementHandler;
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
