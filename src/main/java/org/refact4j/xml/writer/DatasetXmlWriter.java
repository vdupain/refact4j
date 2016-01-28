package org.refact4j.xml.writer;

import org.refact4j.collection.DataSet;
import org.refact4j.core.Finder;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

import java.util.Collection;

public class DatasetXmlWriter implements XmlElementHandler, DatasetConverterHolder {
    private final DataSet dataset;
    private final Dataset2XmlConverterImpl converter;
    private final Finder finder;
    private String tagName = "dataset";

    public DatasetXmlWriter(DataSet dataset, Finder finder, Dataset2XmlConverterImpl converter) {
        this.dataset = dataset;
        this.finder = finder;
        this.converter = converter;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean hasNext() {
        return false;
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        Collection<XmlElementHandler> xmlWriters = converter.getXmlElementHandlers(this);
        return xmlWriters.toArray(new XmlElementHandler[xmlWriters.size()]);
    }

    public Dataset2XmlConverterImpl getDataset2XmlConverter() {
        return this.converter;
    }

    public DataSet getDataSet() {
        return this.dataset;
    }

    public Finder getFinder() {
        return this.finder;
    }

}
