package org.refact4j.xml.reader;

import org.refact4j.collection.DataSet;
import org.refact4j.core.Finder;
import org.refact4j.xml.*;
import org.refact4j.xml.impl.DatasetConverterHolderImpl;

public class DefaultXmlElementReader extends AbstractXmlElement implements DatasetConverterHolder {
    private final DatasetConverterHolder datasetConverterHolder;

    DefaultXmlElementReader(DataSet dataset, Finder finder, DataSet2XmlConverter dataset2XmlConverter) {
        this.datasetConverterHolder = new DatasetConverterHolderImpl(dataset, finder, dataset2XmlConverter);
    }

    public DefaultXmlElementReader(DatasetConverterHolder datasetConverterHolder) {
        this(datasetConverterHolder.getDataSet(), datasetConverterHolder.getFinder(), datasetConverterHolder
                .getDataset2XmlConverter());
    }

    public void add(Object object) {
        this.datasetConverterHolder.getDataSet().add(object);
    }

    public DataSet getDataSet() {
        return this.datasetConverterHolder.getDataSet();
    }

    public DataSet2XmlConverter getDataset2XmlConverter() {
        return this.datasetConverterHolder.getDataset2XmlConverter();
    }

    public Finder getFinder() {
        return this.datasetConverterHolder.getFinder();
    }

    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        throw new RuntimeException("Unknown xml tag '" + localName + "'");
    }

}
