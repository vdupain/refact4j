package org.refact4j.test.reader;

import org.refact4j.test.AssertTrue;
import org.refact4j.test.TestMethod;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;

class AssertTrueXmlElementReader extends AbstractAssertConditionXmlElementReader {

    public AssertTrueXmlElementReader(DatasetConverterHolder dataSetConverterHolder, XmlAttributes attributes,
                                      TestMethod testMethod) {
        super(dataSetConverterHolder, attributes, testMethod, new AssertTrue());
    }

}
