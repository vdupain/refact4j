package org.refact4j.xml.writer;

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

    public java.util.Set getDataSet() {
        return this.holder.getDataSet();
    }

    public DataSet2XmlConverter getDataset2XmlConverter() {
        return this.holder.getDataset2XmlConverter();
    }

}
