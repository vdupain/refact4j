package org.refact4j.xml.reader;

import org.refact4j.collection.Set;
import org.refact4j.core.Finder;
import org.refact4j.xml.DataSet2XmlConverter;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.impl.DatasetConverterHolderImpl;

public class DefaultXmlElementReader implements XmlElement, DatasetConverterHolder {
    private final DatasetConverterHolder datasetConverterHolder;

    DefaultXmlElementReader(Set dataset, Finder finder, DataSet2XmlConverter dataset2XmlConverter) {
        this.datasetConverterHolder = new DatasetConverterHolderImpl(dataset, finder, dataset2XmlConverter);
    }

    public DefaultXmlElementReader(DatasetConverterHolder datasetConverterHolder) {
        this(datasetConverterHolder.getDataSet(), datasetConverterHolder.getFinder(), datasetConverterHolder
                .getDataset2XmlConverter());
    }

    public void add(Object object) {
        this.datasetConverterHolder.getDataSet().add(object);
    }

    public Set getDataSet() {
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
