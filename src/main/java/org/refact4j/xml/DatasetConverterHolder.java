package org.refact4j.xml;

import org.refact4j.core.Finder;

public interface DatasetConverterHolder extends DatasetHolder {

    DataSet2XmlConverter getDataset2XmlConverter();

    Finder getFinder();

}
