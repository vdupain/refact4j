package org.refact4j.test;

import junit.framework.TestSuite;
import org.refact4j.collection.Set;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

public class XmlTestSuiteHelper {

    public static TestSuite unmarshal(String xmlTestSuite) {
        Set dataset = new Set<>();
        Dataset2XmlConverterImpl dataset2XmlConverter = new Dataset2XmlConverterImpl();
        dataset2XmlConverter.register(new TestSuiteXmlDescriptor());
        dataset2XmlConverter.unmarshal("<dataset>" + xmlTestSuite + "</dataset>", dataset);
        return (TestSuite) dataset.stream().findFirst().get();
    }

}
