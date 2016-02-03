package org.refact4j.xml.impl;

import org.refact4j.collection.Set;
import org.refact4j.xml.DataSet2XmlConverter;
import org.refact4j.xml.DataSetConverterHolder;

public class DatasetConverterHolderImpl implements DataSetConverterHolder {
    private final Set dataset;

    private final DataSet2XmlConverter dataset2XmlConverter;

    public DatasetConverterHolderImpl(Set dataset, DataSet2XmlConverter dataset2XmlConverter) {
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
