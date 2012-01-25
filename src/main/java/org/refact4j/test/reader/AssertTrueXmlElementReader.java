package org.refact4j.test.reader;

import org.refact4j.test.AssertTrue;
import org.refact4j.test.TestMethod;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;

class AssertTrueXmlElementReader extends AbstractAssertConditionXmlElementReader {

    public AssertTrueXmlElementReader(DatasetConverterHolder datasetConverterHolder, XmlAttributes attributes,
                                      TestMethod testMethod) {
        super(datasetConverterHolder, attributes, testMethod, new AssertTrue());
    }

}
