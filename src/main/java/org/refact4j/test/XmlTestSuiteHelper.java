package org.refact4j.test;

import junit.framework.TestSuite;

import java.util.HashSet;
import java.util.Set;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

public class XmlTestSuiteHelper {

    public static TestSuite unmarshal(String xmlTestSuite) {
        Set dataset = new HashSet<>();
        Dataset2XmlConverterImpl dataset2XmlConverter = new Dataset2XmlConverterImpl();
        dataset2XmlConverter.register(new TestSuiteXmlDescriptor());
        dataset2XmlConverter.unmarshal("<dataset>" + xmlTestSuite + "</dataset>", dataset);
        return (TestSuite) dataset.stream().findFirst().get();
    }

}
