package org.refact4j.xml.writer;

import org.refact4j.collection.Set;
import org.refact4j.xml.AbstractIteratorXmlElementHandler;
import org.refact4j.xml.DataSet2XmlConverter;
import org.refact4j.xml.DataSetConverterHolder;

import java.util.Collection;

public abstract class AbstractXmlElementWriter extends AbstractIteratorXmlElementHandler implements DataSetConverterHolder {

    private final DataSetConverterHolder holder;

    protected AbstractXmlElementWriter(String tagName, Collection<?> collection, DataSetConverterHolder holder) {
        super(tagName, collection);
        this.holder = holder;
    }

    public Set getDataSet() {
        return this.holder.getDataSet();
    }

    public DataSet2XmlConverter getDataset2XmlConverter() {
        return this.holder.getDataset2XmlConverter();
    }

    public DataSetConverterHolder getDatasetConverterHolder() {
        return this.holder;
    }

}
