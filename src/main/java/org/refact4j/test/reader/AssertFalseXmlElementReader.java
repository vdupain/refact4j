package org.refact4j.test.reader;

import org.refact4j.test.AssertFalse;
import org.refact4j.test.TestMethod;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;

class AssertFalseXmlElementReader extends AbstractAssertConditionXmlElementReader {

    public AssertFalseXmlElementReader(DatasetConverterHolder datasetConverterHolder, XmlAttributes attributes,
                                       TestMethod testMethod) {
        super(datasetConverterHolder, attributes, testMethod, new AssertFalse());
    }

}
