package org.refact4j.xml.impl;

import org.refact4j.collection.Set;
import org.refact4j.xml.Dataset2XmlConverter;
import org.refact4j.xml.DatasetConverterHolder;

public class DatasetConverterHolderImpl implements DatasetConverterHolder {
    private final Set dataset;

    private final Dataset2XmlConverter dataset2XmlConverter;

    public DatasetConverterHolderImpl(Set dataset, Dataset2XmlConverter dataset2XmlConverter) {
        this.dataset = dataset;
        this.dataset2XmlConverter = dataset2XmlConverter;
    }

    public Set getDataSet() {
        return dataset;
    }

    public Dataset2XmlConverter getDataset2XmlConverter() {
        return dataset2XmlConverter;
    }

}
