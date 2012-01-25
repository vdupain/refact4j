package org.refact4j.xml.impl;

import org.refact4j.collection.DataSet;
import org.refact4j.core.Finder;
import org.refact4j.xml.DataSet2XmlConverter;
import org.refact4j.xml.DatasetConverterHolder;

public class DatasetConverterHolderImpl implements DatasetConverterHolder {
    private final DataSet dataset;

    private final Finder finder;

    private final DataSet2XmlConverter dataset2XmlConverter;

    public DatasetConverterHolderImpl(DataSet dataset, Finder finder, DataSet2XmlConverter dataset2XmlConverter) {
        this.dataset = dataset;
        this.finder = finder;
        this.dataset2XmlConverter = dataset2XmlConverter;
    }

    public DataSet getDataSet() {
        return dataset;
    }

    public DataSet2XmlConverter getDataset2XmlConverter() {
        return dataset2XmlConverter;
    }

    public Finder getFinder() {
        return this.finder;
    }

}
