package org.refact4j.xml.writer;

import java.util.Set;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

import java.util.Collection;

public class DatasetXmlWriter implements XmlElementHandler, DatasetConverterHolder {
    private final Set dataset;
    private final Dataset2XmlConverterImpl converter;
    private String tagName = "dataset";

    public DatasetXmlWriter(Set dataset, Dataset2XmlConverterImpl converter) {
        this.dataset = dataset;
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

    public java.util.Set getDataSet() {
        return this.dataset;
    }

}
