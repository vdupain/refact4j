package org.refact4j.xml.reader;

import org.refact4j.collection.Set;
import org.refact4j.xml.DataSet2XmlConverter;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.impl.DatasetConverterHolderImpl;

public class DefaultXmlElementReader implements XmlElement, DataSetConverterHolder {
    private final DataSetConverterHolder dataSetConverterHolder;

    DefaultXmlElementReader(Set dataset, DataSet2XmlConverter dataset2XmlConverter) {
        this.dataSetConverterHolder = new DatasetConverterHolderImpl(dataset, dataset2XmlConverter);
    }

    public DefaultXmlElementReader(DataSetConverterHolder dataSetConverterHolder) {
        this(dataSetConverterHolder.getDataSet(), dataSetConverterHolder
                .getDataset2XmlConverter());
    }

    public void add(Object object) {
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
