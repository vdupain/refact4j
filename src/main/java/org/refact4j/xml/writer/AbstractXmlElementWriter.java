package org.refact4j.xml.writer;

import org.refact4j.collection.DataSet;
import org.refact4j.core.Finder;
import org.refact4j.xml.AbstractIteratorXmlElementHandler;
import org.refact4j.xml.DataSet2XmlConverter;
import org.refact4j.xml.DatasetConverterHolder;

import java.util.Collection;

public abstract class AbstractXmlElementWriter extends AbstractIteratorXmlElementHandler implements DatasetConverterHolder {

    private final DatasetConverterHolder holder;

    protected AbstractXmlElementWriter(String tagName, Collection<?> collection, DatasetConverterHolder holder) {
        super(tagName, collection);
        this.holder = holder;
    }

    public DataSet getDataSet() {
        return this.holder.getDataSet();
    }

    public DataSet2XmlConverter getDataset2XmlConverter() {
        return this.holder.getDataset2XmlConverter();
    }

    public Finder getFinder() {
        return this.holder.getFinder();
    }

    public DatasetConverterHolder getDatasetConverterHolder() {
        return this.holder;
    }

}
