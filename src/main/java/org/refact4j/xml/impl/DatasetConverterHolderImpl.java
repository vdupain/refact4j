package org.refact4j.xml.impl;

import org.refact4j.collection.Set;
import org.refact4j.xml.DataSet2XmlConverter;
import org.refact4j.xml.DatasetConverterHolder;

public class DataSetConverterHolderImpl implements DatasetConverterHolder {
    private final Set dataset;

    private final DataSet2XmlConverter dataset2XmlConverter;

    public DataSetConverterHolderImpl(Set dataset, DataSet2XmlConverter dataset2XmlConverter) {
        this.dataset = dataset;
        this.dataset2XmlConverter = dataset2XmlConverter;
    }

    public Set getDataSet() {
        return dataset;
    }

    public DataSet2XmlConverter getDataset2XmlConverter() {
        return dataset2XmlConverter;
    }

}
