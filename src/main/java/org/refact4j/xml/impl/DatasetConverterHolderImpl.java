package org.refact4j.xml.impl;

import org.refact4j.xml.DataSet2XmlConverter;
import org.refact4j.xml.DatasetConverterHolder;

import java.util.Set;

public class DatasetConverterHolderImpl implements DatasetConverterHolder {
    private final Set dataset;

    private final DataSet2XmlConverter dataset2XmlConverter;

    public DatasetConverterHolderImpl(Set dataset, DataSet2XmlConverter dataset2XmlConverter) {
        this.dataset = dataset;
        this.dataset2XmlConverter = dataset2XmlConverter;
    }

    public java.util.Set getDataSet() {
        return dataset;
    }

    public DataSet2XmlConverter getDataset2XmlConverter() {
        return dataset2XmlConverter;
    }

}
