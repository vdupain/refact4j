package org.refact4j.xml.reader;

import org.refact4j.collection.Set;
import org.refact4j.xml.DataSet2XmlConverter;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.impl.DatasetConverterHolderImpl;

public class DefaultXmlElementReader implements XmlElement, DatasetConverterHolder {
    private final DatasetConverterHolder dataSetConverterHolder;

    DefaultXmlElementReader(Set dataset, DataSet2XmlConverter dataset2XmlConverter) {
        this.dataSetConverterHolder = new DatasetConverterHolderImpl(dataset, dataset2XmlConverter);
    }

    protected DefaultXmlElementReader(DatasetConverterHolder dataSetConverterHolder) {
        this(dataSetConverterHolder.getDataSet(), dataSetConverterHolder
                .getDataset2XmlConverter());
    }

    protected void add(Object object) {
        this.dataSetConverterHolder.getDataSet().add(object);
    }

    public Set getDataSet() {
        return this.dataSetConverterHolder.getDataSet();
    }

    public DataSet2XmlConverter getDataset2XmlConverter() {
        return this.dataSetConverterHolder.getDataset2XmlConverter();
    }

    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        throw new RuntimeException("Unknown xml tag '" + localName + "'");
    }

}
